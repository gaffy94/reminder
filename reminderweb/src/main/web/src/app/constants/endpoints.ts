export const link = {

  baseaddress: 'http://localhost:9089/api',
  login: 'http://localhost:9089/login',
  logout: 'http://localhost:9089/logout',



};

export const fetchTenants = {
  apiUrl: link.baseaddress + '/tenants/fetchall'
};

export const doToggle = {
  apiUrl: link.baseaddress + '/tenants/togglepaid/{id}'
}

export const resetAll = {
  apiUrl: link.baseaddress + '/tenants/reset'
}