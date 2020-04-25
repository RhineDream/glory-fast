// 引入文件
import request from '@/utils/axios-request';

/**
 *  获取用户列表
 * @method getUserList
 * @return {[type]}         [description]
 */
export function getUserList(param) {
  return request({
    url: '/api/user/list',
    method: 'post',
    data: param,
  });
}

export function getLogin(param) {
  return request({
    url: '/api/auth/login',
    method: 'post',
    data: param,
  });
}
