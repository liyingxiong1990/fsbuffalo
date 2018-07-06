export default {
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
  }
}
