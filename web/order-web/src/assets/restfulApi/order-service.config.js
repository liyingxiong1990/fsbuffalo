export default {
  driver: { // 专卖店司机
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
  store: { // 专卖店
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
  }
}
