import Cookies from 'js-cookie';

import { routerRedux } from 'dva/router';
import { fakeAccountLogin, fakeMobileLogin } from '../services/api';

export default {
  namespace: 'login',

  state: {
    status: Cookies.get('msxToken') ? 'ok' : undefined,
  },

  effects: {
    *accountSubmit({ payload }, { call, put }) {
      yield put({
        type: 'changeSubmitting',
        payload: true,
      });
      const response = yield call(fakeAccountLogin, payload);
      if (response && response.status === 'ok') {
        Cookies.set('msxToken', 'msxToken', 1);
      }
      yield put({
        type: 'changeLoginStatus',
        payload: response,
      });
      yield put({
        type: 'changeSubmitting',
        payload: false,
      });
    },
    *mobileSubmit(_, { call, put }) {
      yield put({
        type: 'changeSubmitting',
        payload: true,
      });
      const response = yield call(fakeMobileLogin);
      if (response && response.status === 'ok') {
        Cookies.set('msxToken', 'msxToken', 1);
      }
      yield put({
        type: 'changeLoginStatus',
        payload: response,
      });
      yield put({
        type: 'changeSubmitting',
        payload: false,
      });
    },
    *alreadyLogin(_, { put }) {
      yield put({
        type: 'changeLoginStatus',
        payload: { status: 'ok', type: 'account' },
      });
    },
    *logout(_, { put }) {
      yield put({
        type: 'changeLoginStatus',
        payload: {
          status: false,
        },
      });
      yield put(routerRedux.push('/user/login'));
    },
  },

  reducers: {
    changeLoginStatus(state, { payload }) {
      return {
        ...state,
        status: payload.status,
        type: payload.type,
      };
    },
    changeSubmitting(state, { payload }) {
      return {
        ...state,
        submitting: payload,
      };
    },
  },
};
