export default {
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
  }
}
