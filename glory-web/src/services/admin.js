import request1 from '@/utils/request1';

export async function list(params) {
  return request1('/user/list2', {
    method: 'POST',
    // data: {
    //   id: params
    // }
    data: params,
  });
}
