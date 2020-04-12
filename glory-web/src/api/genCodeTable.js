import { axios } from '@/utils/request'

const api = {
  list: '/genCodeTable/list'
}

export default api

export function getGenCodeTableList (parameter) {
  return axios({
    url: api.list,
    method: 'get',
    params: parameter
  })
}

// id == 0 add     post
// id != 0 update  put
export function saveService (parameter) {
  return axios({
    url: api.service,
    method: parameter.id === 0 ? 'post' : 'put',
    data: parameter
  })
}
