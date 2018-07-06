export default {
  auth: {
    login: {
      url: '/oauth/token',
      method: 'post'
    },
    info: {
      url: '/oauth/info/{username}',
      method: 'get'
    }
  },
  sysDepartment: {
    getAll: {
      url: '/sys/department',
      method: 'get'
    },
    getById: {
      url: '/sys/department/{dept_id}',
      method: 'get'
    },
    add: {
      url: '/sys/department',
      method: 'post'
    },
    update: {
      url: '/sys/department',
      method: 'put'
    },
    delete: {
      url: '/sys/department/{dept_id}',
      method: 'delete'
    }
  },
  sysDict: {
    getAll: {
      url: '/sys/dict',
      method: 'get'
    },
    getListByDictType: {
      url: '/sys/dict/{dict_type}',
      method: 'get'
    },
    add: {
      url: '/sys/dict',
      method: 'post'
    },
    update: {
      url: '/sys/dict',
      method: 'put'
    },
    delete: {
      url: '/sys/dict/{dict_id}',
      method: 'delete'
    }
  },
  sysUser: {
    getAll: {
      url: '/sys/user',
      method: 'get'
    },
    getById: {
      url: '/sys/user/{user_id}',
      method: 'get'
    },
    add: {
      url: '/sys/user',
      method: 'post'
    },
    update: {
      url: '/sys/user',
      method: 'put'
    },
    updatePwd: {
      url: '/sys/user/pwd',
      method: 'put'
    },
    delete: {
      url: '/sys/user/{user_id}',
      method: 'delete'
    },
    info: {
      url: '/sys/user/info/{login_name}',
      method: 'get'
    }
  },
  sysRole: {  // 角色
    getAll: {
      url: '/sys/role',
      method: 'get'
    },
    getByAcctId: {
      url: '/sys/role/{role_id}',
      method: 'get'
    },
    add: {
      url: '/sys/role',
      method: 'post'
    },
    update: {
      url: '/sys/role',
      method: 'put'
    },
    delete: {
      url: '/sys/role/{role_id}',
      method: 'delete'
    }
  },
  sysMenu: {  // 系统目录
    getAll: {
      url: '/sys/menu',
      method: 'get'
    },
    getById: {
      url: '/sys/menu/{menu_id}',
      method: 'get'
    },
    add: {
      url: '/sys/menu',
      method: 'post'
    },
    update: {
      url: '/sys/menu',
      method: 'put'
    },
    delete: {
      url: '/sys/menu/{menu_id}',
      method: 'delete'
    }
  },
  sysRolePermission: {  // 系统角色权限操作
    getListByRoleId: {
      url: '/sys/role/permission/rid/{role_id}',
      method: 'get'
    },
    getListByPermissionId: {
      url: '/sys/role/permission/pid/{role_permission_id}',
      method: 'get'
    },
    update: { // {"role_id":"aaaa", "role_permissions":["asd","asd","1sad"]} role_permissions-permission_id
      url: '/sys/role/permission',
      method: 'put'
    }
  },
  sysPermission: {  // 菜单权限
    getAll: {
      url: '/sys/permission',
      method: 'get'
    },
    getById: {
      url: '/sys/permission/{permission_id}',
      method: 'get'
    },
    getByMenuId: {
      url: '/sys/permission/menu/{menu_id}',
      method: 'get'
    },
    add: {
      url: '/sys/permission',
      method: 'post'
    },
    update: {
      url: '/sys/permission',
      method: 'put'
    },
    delete: {
      url: '/sys/permission/{permission_id}',
      method: 'delete'
    }
  },
  sysRoleUser: {  // 用户角色关系
    getByRoleId: {
      url: '/sys/role/user/rid/{role_id}',
      method: 'get'
    },
    getByUserId: {
      url: '/sys/role/user/uid/{user_id}',
      method: 'get'
    },
    add: { // {role_user_id: '', userIdList: []}
      url: '/sys/role/user',
      method: 'post'
    },
    update: {
      url: '/sys/role/user',
      method: 'put'
    },
    delete: {
      url: '/sys/role/user/{role_user_id}',
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
    updatePassword: {
      url: '/acct/customer/pwd',
      method: 'put'
    },
    delete: {
      url: '/acct/customer/{cust_id}',
      method: 'delete'
    }
  },
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
  regHold: {
    getAll: {
      url: '/reg/hold',
      method: 'get'
    },
    getBySecuIdAndType: {
      url: '/reg/hold/{secu_id}/{secu_type}',
      method: 'get'
    },
    getBySecuCodeType: {
      url: '/reg/hold/getBySecuCodeType/{secu_code}/{secu_type}',
      method: 'get'
    },
    add: {
      url: '/reg/hold',
      method: 'post'
    },
    update: {
      url: '/reg/hold',
      method: 'put'
    },
    delte: {
      url: '/reg/hold/{hold_id}',
      method: 'delete'
    },
    getByHoldId: {
      url: '/reg/hold/{hold_id}',
      method: 'get'
    },
    getListByAcctId: {
      url: '/reg/hold/acct/{acct_id}',
      method: 'get'
    }
  },
  nshHoldRight: {
    getByHoldId: {
      url: '/nsh/hold/right/{id}',
      method: 'get'
    },
    getListByHoldId: {
      url: '/nsh/hold/right/hold/{hold_id}',
      method: 'get'
    },
    add: {
      url: '/nsh/hold/right',
      method: 'post'
    },
    update: {
      url: '/nsh/hold/right',
      method: 'put'
    }
  },
  nshRight: {
    getAll: {
      url: '/nsh/right',
      method: 'get'
    },
    getRightById: {
      url: '/nsh/right/{id}',
      method: 'get'
    },
    getRightListByHoldId: {
      url: '/nsh/right/list/{hold_id}',
      method: 'get'
    },
    add: {
      url: '/nsh/right',
      method: 'post'
    }
  },
  regHoldAccount: {
    getAll: {
      url: '/reg/hold/account',
      method: 'get'
    },
    getByHoldId: {
      url: '/reg/hold/account/hold/{reg_hold_id}',
      method: 'get'
    },
    add: {
      url: '/reg/hold/account',
      method: 'post'
    },
    update: {
      url: '/reg/hold/account',
      method: 'put'
    },
    delte: {
      url: '/reg/hold/account/{id}',
      method: 'delete'
    }
  },
  regBond: {
    getAll: {
      url: '/reg/bond',
      method: 'get'
    },
    getByBondId: {
      url: '/reg/bond/{bond_id}',
      method: 'get'
    },
    add: {
      url: '/reg/bond',
      method: 'post'
    },
    update: {
      url: '/reg/bond',
      method: 'put'
    },
    updateState: {
      url: '/reg/bond/state',
      method: 'put'
    }
  },
  regEquity: {
    getAll: {
      url: '/reg/equity',
      method: 'get'
    },
    getByEquitytId: {
      url: '/reg/equity/{equity_id}',
      method: 'get'
    },
    add: {
      url: '/reg/equity',
      method: 'post'
    },
    update: {
      url: '/reg/equity',
      method: 'put'
    },
    updateState: {
      url: '/reg/equity/state',
      method: 'put'
    }
  },
  regStock: {
    getAll: {
      url: '/reg/stock',
      method: 'get'
    },
    getByStocktId: {
      url: '/reg/stock/{stock_id}',
      method: 'get'
    },
    add: {
      url: '/reg/stock',
      method: 'post'
    },
    update: {
      url: '/reg/stock',
      method: 'put'
    },
    updateState: {
      url: '/reg/stock/state',
      method: 'put'
    }
  },
  orderCapitalChange: {
    getAll: {
      url: '/order/capital/change',
      method: 'get'
    },
    getById: {
      url: '/order/capital/change/{capital_change_id}',
      method: 'get'
    },
    getBySecuIdType: {
      url: '/order/capital/change/secu/{secu_id}/{secu_type}',
      method: 'get'
    },
    getRecordsByCapitalChangeId: {
      url: '/order/capital/change/record/{capital_change_id}',
      method: 'get'
    },
    add: {
      url: '/order/capital/change',
      method: 'post'
    }
  },
  orderPledge: {
    getAll: {
      url: '/order/pledge',
      method: 'get'
    },
    getByPledgetId: {
      url: '/order/pledge/{pledge_id}',
      method: 'get'
    },
    getRecordListByPledgeId: {
      url: '/order/pledge/records/{pledge_id}',
      method: 'get'
    },
    // 预质押
    add: {
      url: '/order/pledge/pre',
      method: 'post'
    },
    // 确认质押
    confirm: {
      url: '/order/pledge/confirm',
      method: 'post'
    },
    // 质押变更
    update: {
      url: '/order/pledge',
      method: 'put'
    },
    // 解除预质押
    preCancel: {
      url: '/order/pledge/pre/cancel/{pledge_id}',
      method: 'put'
    },
    // 解除质押
    cancel: {
      url: '/order/pledge/cancel/{pledge_id}',
      method: 'put'
    },
    revoke: {
      url: '/order/pledge/revoke/{pledge_id}',
      method: 'put'
    }
  },
  orderTransfer: {
    getAll: {
      url: '/order/transfer',
      method: 'get'
    },
    getByTransfertId: {
      url: '/order/transfer/{transfer_id}',
      method: 'get'
    },
    getRecordListByTransfertId: {
      url: '/order/transfer/records/{transfer_id}',
      method: 'get'
    },
    add: {
      url: '/order/transfer/transfer',
      method: 'post'
    }
  },
  orderFreeze: {
    getAll: {
      url: '/order/freeze',
      method: 'get'
    },
    getByFreezetId: {
      url: '/order/freeze/{freeze_id}',
      method: 'get'
    },
    getRecordListByFreezeId: {
      url: '/order/freeze/records/{freeze_id}',
      method: 'get'
    },
    add: {
      url: '/order/freeze',
      method: 'post'
    },
    update: {
      url: '/order/freeze',
      method: 'put'
    },
    cancel: {
      url: '/order/freeze/cancel/{freeze_id}',
      method: 'put'
    },
    revoke: {
      url: '/order/freeze/revoke/{freeze_id}',
      method: 'put'
    }
  },
  orderCancel: {
    getAll: {
      url: '/order/cancel',
      method: 'get'
    },
    getByCanceltId: {
      url: '/order/cancel/{cancel_id}',
      method: 'get'
    },
    cancel: {
      url: '/order/cancel/cancel',
      method: 'post'
    }
  },
  operateLog: {
    getAll: {
      url: '/sys/operate/log',
      method: 'get'
    }
  },
  requestLog: {
    getAll: {
      url: '/sys/request/log',
      method: 'get'
    }
  },
  ppjhStatistic: {
    fiveBillion: {
      url: '/ppjh/statistic/income/over/5/billion',
      method: 'get'
    },
    byTown: {
      url: '/ppjh/statistic/enterprise/by/town',
      method: 'get'
    },
    tenMillion: {
      url: '/ppjh/statistic/income/over/10/million',
      method: 'get'
    },
    brandAnalysis: {
      url: '/ppjh/statistic/brand/analysis',
      method: 'get'
    },
    patentAnalysis: {
      url: '/ppjh/statistic/patent/analysis',
      method: 'get'
    },
    regAnalysis: {
      url: '/ppjh/statistic/patent/high/reg/analysis',
      method: 'get'
    },
    operationAnalysis: {
      url: '/ppjh/statistic/market/operation/analysis',
      method: 'get'
    },
    recognitionAnalysis: {
      url: '/ppjh/statistic/high/recognition/analysis',
      method: 'get'
    },
    notregAnalysis: {
      url: '/ppjh/statistic/not/high/reg/analysis',
      method: 'get'
    },
    finalistTown: {
      url: '/ppjh/statistic/finalist/enterprise/by/town',
      method: 'get'
    },
    marketFinallist: {
      url: '/ppjh/statistic/market/operation/finallist/analysis',
      method: 'get'
    },
    patentFinallist: {
      url: '/ppjh/statistic/patent/finallist/analysis',
      method: 'get'
    },
    patentHighFinallist: {
      url: '/ppjh/statistic/patent/high/reg/finallist/analysis',
      method: 'get'
    },
    brandFinallist: {
      url: '/ppjh/statistic/brand/finallist/analysis',
      method: 'get'
    },
    highFinallist: {
      url: '/ppjh/statistic/high/reg/finallist/analysis',
      method: 'get'
    },
    notHighFinallist: {
      url: '/ppjh/statistic/not/high/reg/analysis',
      method: 'get'
    }
  },
  ppjhDeclaration: {
    getAll: {
      url: '/ppjh/declaration',
      method: 'get'
    }
  },
  ppjhEvaluation: {
    getAll: {
      url: '/ppjh/evaluation',
      method: 'get'
    }
  },
  ppjhEnterprise: {
    getAll: {
      url: '/ppjh/enterprise',
      method: 'get'
    }
  },
  orgManagement: {
    getAll: {
      url: '/org/management',
      method: 'get'
    },
    add: {
      url: '/org/management',
      method: 'post'
    },
    update: {
      url: '/org/management',
      method: 'put'
    },
    delete: {
      url: '/org/management',
      method: 'delete'
    }
  },
  orgEmployee: {
    getAll: {
      url: '/org/employee',
      method: 'get'
    },
    add: {
      url: '/org/employee',
      method: 'post'
    },
    update: {
      url: '/org/employee',
      method: 'put'
    },
    delete: {
      url: '/org/employee',
      method: 'delete'
    }
  },
  nshMajorShareholder: {
    getAll: {
      url: '/nsh/major/shareholder',
      method: 'get'
    },
    add: {
      url: '/nsh/major/shareholder',
      method: 'post'
    },
    delete: {
      url: '/nsh/major/shareholder/{id}',
      method: 'delete'
    }
  },
  nshActualController: {
    getAll: {
      url: '/nsh/actual/controller',
      method: 'get'
    },
    add: {
      url: '/nsh/actual/controller',
      method: 'post'
    },
    update: {
      url: '/nsh/actual/controller',
      method: 'put'
    },
    delete: {
      url: '/nsh/actual/controller/{id}',
      method: 'delete'
    }
  },
  nshFinalBeneficiary: {
    getAll: {
      url: '/nsh/final/beneficiary',
      method: 'get'
    },
    add: {
      url: '/nsh/final/beneficiary',
      method: 'post'
    },
    update: {
      url: '/nsh/final/beneficiary',
      method: 'put'
    },
    delete: {
      url: '/nsh/final/beneficiary/{id}',
      method: 'delete'
    }
  },
  nshMainInvestor: {
    getAll: {
      url: '/nsh/main/investor',
      method: 'get'
    },
    add: {
      url: '/nsh/main/investor',
      method: 'post'
    },
    update: {
      url: '/nsh/main/investor',
      method: 'put'
    },
    delete: {
      url: '/nsh/main/investor/{id}',
      method: 'delete'
    }
  },
  nshSignificantInfluence: {
    getAll: {
      url: '/nsh/significant/influence',
      method: 'get'
    },
    add: {
      url: '/nsh/significant/influence',
      method: 'post'
    },
    update: {
      url: '/nsh/significant/influence',
      method: 'put'
    },
    delete: {
      url: '/nsh/significant/influence/{id}',
      method: 'delete'
    }
  },
  fastDFSAttachment: {
    DEFUALT_API: 'fastDFS',
    getByIdAndType: {
      url: '/fastdfs/attachment/get/{order_id}/{order_type}',
      method: 'get'
    },
    add: {
      url: '/fastdfs/attachment/add',
      method: 'post'
    },
    update: {
      url: '/fastdfs/attachment/update',
      method: 'put'
    },
    delete: {
      url: '/fastdfs/attachment/delete/{order_id}/{order_type}',
      method: 'delete'
    }
  },
  fastDFS: {
    DEFUALT_API: 'fastDFS',
    getByKey: {
      url: '/fastdfs/download/{key}',
      method: 'get'
    },
    add: {
      url: '/fastdfs/upload',
      method: 'post'
    },
    delete: {
      url: '/fastdfs/delete/{key}',
      method: 'delete'
    }
  }
}
