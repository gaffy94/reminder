export const link = {

  // baseaddress: 'http://localhost:9089/api',
  // login: 'http://localhost:9089/login',
  // logout: 'http://localhost:9089/logout',
  baseaddress: 'https://reminder-275016.ey.r.appspot.com/api',
  login: 'https://reminder-275016.ey.r.appspot.com/login',
  logout: 'https://reminder-275016.ey.r.appspot.com/logout',

  

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