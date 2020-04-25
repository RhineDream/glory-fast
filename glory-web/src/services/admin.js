import request1 from '@/utils/request1';

export async function list(params) {
  return request1('/user/list2', {
    method: 'POST',
    data: params,
  });
}

export async function save(params) {
  return request1('/user/save', {
    method: 'POST',
    data: params,
  });
}

export async function getById(params) {
  return request1('/user/getById', {
    method: 'POST',
    data: params,
  });
}

export async function edit(params) {
  return request1('/user/edit', {
    method: 'POST',
    data: params,
  });
}
