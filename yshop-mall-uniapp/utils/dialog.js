const dialog = {
  confirm: options => {
    uni.showModal({
      title: '提示',
      content: options.mes,
      success(res) {
        if (res.confirm) {
          opts()
        } else if (res.cancel) {
        }
      },
    })
  },
  alert: null,
  // alert: Dialog.alert,
  notify: null,
  // notify,
  loading: {
    open: () => {
      // uni.showLoading({
      // 	title: '加载中'
      // })
    },
    close: () => {
      uni.hideLoading()
    },
  },
}

// const icons = { error: "操作失败", success: "操作成功" };
// Object.keys(icons).reduce((dialog, key) => {
//   dialog[key] = (mes, obj = {}) => {
//     return new Promise(function (resolve) {
//       toast({
//         mes: mes || icons[key],
//         timeout: 1000,
//         icon: key,
//         callback: () => {
//           resolve();
//         },
//         ...obj
//       });
//     });
//   };
//   return dialog;
// }, dialog);

dialog.message = (mes = '操作失败', obj = {}) => {
  return new Promise(function(resolve) {
    uni.showToast({
      title: mes,
      icon: 'none',
      duration: 2000,
      complete: () => {
        resolve()
      },
    })
  })
}

dialog.toast = options => {
  uni.showToast({
    title: options.mes,
    icon: 'none',
    duration: 2000,
    complete: () => {
      options.callback ? options.callback() : null
    },
  })
}

dialog.error = mes => {
  uni.showToast({
    title: mes,
    icon: 'none',
    duration: 2000,
  })
}

dialog.validateError = (...args) => {
  validatorDefaultCatch(...args)
}

export function validatorDefaultCatch(err, type = 'message') {
  uni.showToast({
    title: err.errors[0].message,
    icon: 'none',
    duration: 2000,
  })
  return false
}

export default dialog
