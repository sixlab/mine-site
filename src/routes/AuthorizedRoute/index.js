/*
 * Copyright (c) 2017 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/11/27 17:25
 * @Author: Patrick <root@sixlab.cn>
 */
import React, { PureComponent } from 'react';
import { Route, Redirect } from 'dva/router';
import { connect } from 'dva';

import Cookie from 'js-cookie';

@connect(state => ({
  login: state.login,
}))
export default class extends PureComponent {
  render() {
    const { render, login, ...rest } = this.props;

    if (login && login.status === 'ok') {
      return render(this.props);
    }

    const msxToken = Cookie.get('msxToken');
    if (msxToken) {
      return render(this.props);
    }

    return (
      <Route
        {...rest}
        render={(props) => {
          // 根据用户权限，结合即将要访问的页面，判断是否渲染
          const { pathname } = props.location;
          if (pathname === '/exception/500') {
            // 这里，用户永远无法访问500页面
            return <Redirect to="/exception/403" />;
          }
          // 在此可以检验登录状态，强制要求用户先登录
          return <Redirect to="/user/login" />;
        }}
      />
    );
  }
}
