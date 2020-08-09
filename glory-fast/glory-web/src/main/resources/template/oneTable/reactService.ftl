import request from '@/utils/request';
import { func } from 'prop-types';

/*列表查询*/
export async function query${(tableInfo.clazzName)!""}List(data) {
  return request('/api/${(lowerClazzName)!""}/list', {
    method: 'POST',
    data,
  });
}

/*新增*/
export async function add${(tableInfo.clazzName)!""}(data) {
  return request('/api/${(lowerClazzName)!""}/insert', {
    method: 'POST',
    data,
  });
}

/*修改*/
export async function edit${(tableInfo.clazzName)!""}(data) {
    return request('/api/${(lowerClazzName)!""}/update', {
      method: 'PUT',
      data,
    });
  }

/*删除or批量*/
export async function delete${(tableInfo.clazzName)!""}(data) {
    let param = data.data
    return request('/api/${(lowerClazzName)!""}/delete', {
        method: 'DELETE',
        data:param
    });
}
