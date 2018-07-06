export default {
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
