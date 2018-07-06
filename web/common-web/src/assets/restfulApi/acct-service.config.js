export default {
  acctCustomerOrg: { // 机构客户信息
    getAll: {
      url: '/acct/customer/org',
      method: 'get'
    },
    detailInfoList: {
      url: '/acct/customer/org/detail',
      method: 'get'
    },
    getByCustId: {
      url: '/acct/customer/org/{cust_id}',
      method: 'get'
    },
    getByRepcertNo: {
      url: '/acct/customer/org/repcertno/{rep_cert_no}',
      method: 'get'
    },
    add: {
      url: '/acct/customer/org',
      method: 'post'
    },
    update: {
      url: '/acct/customer/org',
      method: 'put'
    },
    delete: {
      url: '/acct/customer/org/{cust_id}',
      method: 'delete'
    }
  },
  acctCustomerPerson: { // 个人客户信息
    getAll: {
      url: '/acct/customer/person',
      method: 'get'
    },
    detailInfoList: {
      url: '/acct/customer/person/detail',
      method: 'get'
    },
    getByCustId: {
      url: '/acct/customer/person/{cust_id}',
      method: 'get'
    },
    getByCertId: {
      url: '/acct/customer/person/certid/{cert_id}',
      method: 'get'
    },
    add: {
      url: '/acct/customer/person',
      method: 'post'
    },
    update: {
      url: '/acct/customer/person',
      method: 'put'
    },
    delete: {
      url: '/acct/customer/person/{cust_id}',
      method: 'delete'
    }
  },
  acctCustomerCert: {
    getAll: {
      url: '/acct/customer/cert',
      method: 'get'
    },
    getByCustId: {
      url: '/acct/customer/cert/cust/{cust_id}',
      method: 'get'
    },
    add: {
      url: '/acct/customer/cert',
      method: 'post'
    },
    update: {
      url: '/acct/customer/cert',
      method: 'put'
    },
    delete: {
      url: '/acct/customer/cert/{cust_id}',
      method: 'delete'
    }
  },
  acctAccount: {
    getAll: {
      url: '/acct/account',
      method: 'get'
    },
    getByAcctId: {
      url: '/acct/account/acct/{acct_id}',
      method: 'get'
    },
    getListByCustId: {
      url: '/acct/account/cust/{cust_id}',
      method: 'get'
    },
    add: {
      url: '/acct/account',
      method: 'post'
    },
    update: {
      url: '/acct/account',
      method: 'put'
    },
    updateState: {
      url: '/acct/account/state',
      method: 'put'
    },
    freeze: {
      url: '/acct/account/freeze/{acct_id}',
      method: 'put'
    },
    unfreeze: {
      url: '/acct/account/unfreeze/{acct_id}',
      method: 'put'
    },
    cancel: {
      url: '/acct/account/cancel/{acct_id}',
      method: 'put'
    }
  },
  acctBankAccount: {
    getAll: {
      url: '/acct/bank/account',
      method: 'get'
    },
    getByAcctId: {
      url: '/acct/bank/account/{id}',
      method: 'get'
    },
    add: {
      url: '/acct/bank/account',
      method: 'post'
    },
    update: {
      url: '/acct/bank/account',
      method: 'put'
    },
    delte: {
      url: '/acct/bank/account/{id}',
      method: 'delete'
    }
  },
  acctCustomer: { // 客户基本信息
    getAll: {
      url: '/acct/customer',
      method: 'get'
    },
    getByCustId: {
      url: '/acct/customer/{cust_id}',
      method: 'get'
    },
    add: {
      url: '/acct/customer',
      method: 'post'
    },
    update: {
      url: '/acct/customer',
      method: 'put'
    },
    updateState: {
      url: '/acct/customer/state',
      method: 'put'
    },
    freeze: {
      url: '/acct/customer/freeze/{cust_id}',
      method: 'put'
    },
    unfreeze: {
      url: '/acct/customer/unfreeze/{cust_id}',
      method: 'put'
    },
    cancel: {
      url: '/acct/customer/cancel/{cust_id}',
      method: 'put'
    },
    updateLoginPassword: {
      url: '/acct/customer/login/pwd',
      method: 'put'
    },
    updateTradePassword: {
      url: '/acct/customer/trade/pwd',
      method: 'put'
    },
    delete: {
      url: '/acct/customer/{cust_id}',
      method: 'delete'
    }
  }
}
