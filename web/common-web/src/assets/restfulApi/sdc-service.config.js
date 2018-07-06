export default {
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
  regCapitalChange: {
    getAll: {
      url: '/reg/capital/change',
      method: 'get'
    },
    getById: {
      url: '/reg/capital/change/{capital_change_id}',
      method: 'get'
    },
    getBySecuIdType: {
      url: '/reg/capital/change/secu/{secu_id}/{secu_type}',
      method: 'get'
    },
    getRecordsByCapitalChangeId: {
      url: '/reg/capital/change/record/{capital_change_id}',
      method: 'get'
    },
    add: {
      url: '/reg/capital/change',
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
    // 确认质押
    add: {
      url: '/order/pledge',
      method: 'post'
    },
    // 质押变更
    // update: {
    //   url: '/order/pledge',
    //   method: 'put'
    // },
    // 解除质押
    cancel: {
      url: '/order/pledge/cancel',
      method: 'post'
    }
    // revoke: {
    //   url: '/order/pledge/revoke/{pledge_id}',
    //   method: 'put'
    // }
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
      url: '/order/freeze/cancel',
      method: 'post'
    }
  },
  regCancel: {
    getAll: {
      url: '/reg/cancel',
      method: 'get'
    },
    getByCanceltId: {
      url: '/reg/cancel/{cancel_id}',
      method: 'get'
    },
    cancel: {
      url: '/reg/cancel/cancel',
      method: 'post'
    }
  },
  entEmployee: {
    getAll: {
      url: '/ent/info/employee',
      method: 'get'
    },
    add: {
      url: '/ent/info/employee',
      method: 'post'
    },
    update: {
      url: '/ent/info/employeee',
      method: 'put'
    },
    delete: {
      url: '/ent/info/employee',
      method: 'delete'
    }
  },
  entManagement: {
    getAll: {
      url: '/ent/info/management',
      method: 'get'
    },
    add: {
      url: '/ent/info/management',
      method: 'post'
    },
    update: {
      url: '/ent/info/management',
      method: 'put'
    },
    delete: {
      url: '/ent/info/management',
      method: 'delete'
    }
  },
  regInitial: {
    add: {
      url: '/reg/initial/registration',
      method: 'post'
    }
  }
}
