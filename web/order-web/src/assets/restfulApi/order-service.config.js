export default {
  driver: { // 专卖店司机
    getDriverList: {
      url: '/order/enterprise/driver/all',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/driver',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/driver',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/driver',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/driver/delete',
      method: 'put'
    }
  },
  deliverer: { // 外县市司机
    getDelivererList: {
      url: '/order/enterprise/deliverer/all',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/deliverer',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/deliverer',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/deliverer',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/deliverer/delete',
      method: 'put'
    }
  },
  outOrderRecorder: { // 开单人
    getOutOrderRecorderList: {
      url: '/order/enterprise/outOrderRecorder/all',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/outOrderRecorder',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/outOrderRecorder',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/outOrderRecorder',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/outOrderRecorder/delete',
      method: 'put'
    }
  },
  inOrderRecorder: { // 仓管
    getInOrderRecorderList: {
      url: '/order/enterprise/inOrderRecorder/all',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/inOrderRecorder',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/inOrderRecorder',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/inOrderRecorder',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/inOrderRecorder/delete',
      method: 'put'
    }
  },
  carrier: { // 缴仓
    getCarrierList: {
      url: '/order/enterprise/carrier/all',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/carrier',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/carrier',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/carrier',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/carrier/delete',
      method: 'put'
    }
  },
  product: { // 产品
    getProductList: {
      url: '/order/enterprise/product/all',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/product',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/product',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/product',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/product/delete',
      method: 'put'
    }
  },
  line: { // 路线
    getLineList: {
      url: '/order/enterprise/line/all',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/line',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/line',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/line',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/line/delete',
      method: 'put'
    }
  },
  store: { // 专卖店
    getStoreList: {
      url: '/order/enterprise/store/all',
      method: 'get'
    },
    getStoresByLine: {
      url: '/order/enterprise/store/line',
      method: 'get'
    },
    getAll: {
      url: '/order/enterprise/store',
      method: 'get'
    },
    add: {
      url: '/order/enterprise/store',
      method: 'post'
    },
    update: {
      url: '/order/enterprise/store',
      method: 'put'
    },
    delete: {
      url: '/order/enterprise/store/delete',
      method: 'put'
    }
  },
  inventory: { // 库存
    getAll: {
      url: '/order/order/inventory',
      method: 'get'
    },
    getById: {
      url: '/order/order/inventory/{id}',
      method: 'get'
    },
    getByDate: {
      url: '/order/order/inventory/date',
      method: 'post'
    },
    add: {
      url: '/order/order/inventory',
      method: 'post'
    },
    add_blank: {
      url: '/order/order/inventory/blank',
      method: 'post'
    },
    update: {
      url: '/order/order/inventory',
      method: 'put'
    },
    delete: {
      url: '/order/order/inventory/delete',
      method: 'put'
    }
  },
  checkinOrder: { // 进仓单
    getAll: {
      url: '/order/order/checkinOrder',
      method: 'get'
    },
    getById: {
      url: '/order/order/checkinOrder/{id}',
      method: 'get'
    },
    add: {
      url: '/order/order/checkinOrder',
      method: 'post'
    },
    update: {
      url: '/order/order/checkinOrder',
      method: 'put'
    },
    delete: {
      url: '/order/order/checkinOrder/delete',
      method: 'put'
    },
    statistic: {
      url: '/order/order/checkinOrder/statistic',
      method: 'post'
    }
  },
  delivererOrder: { // 送货单
    getAll: {
      url: '/order/order/delivererOrder',
      method: 'get'
    },
    getById: {
      url: '/order/order/delivererOrder/{id}',
      method: 'get'
    },
    getByDateDriverLine: {
      url: '/order/order/delivererOrder/getByDateDriverLine',
      method: 'post'
    },
    add: {
      url: '/order/order/delivererOrder',
      method: 'post'
    },
    update: {
      url: '/order/order/delivererOrder',
      method: 'put'
    },
    delete: {
      url: '/order/order/delivererOrder/delete',
      method: 'put'
    }
  },
  warehouseOrder: { // 出仓单
    getAll: {
      url: '/order/order/warehouseOrder',
      method: 'get'
    },
    getById: {
      url: '/order/order/warehouseOrder/{id}',
      method: 'get'
    },
    add: {
      url: '/order/order/warehouseOrder',
      method: 'post'
    },
    delete: {
      url: '/order/order/warehouseOrder/delete',
      method: 'put'
    }
  }
}
