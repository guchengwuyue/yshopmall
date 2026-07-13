const bindMessage = (fn, message) => {
  fn.message = field => message.replace("%s", field || "");
};

export function required(message, opt = {}) {
  return {
    required: true,
    message,
    type: "string",
    ...opt
  };
}

bindMessage(required, "请输入%s");

export function url(message, opt = {}) {
  return {
    type: "url",
    message,
    ...opt
  };
}

bindMessage(url, "请输入正确的链接");

export function email(message, opt = {}) {
  return {
    type: "email",
    message,
    ...opt
  };
}

bindMessage(email, "请输入正确的邮箱地址");

/**
 * 验证字段必须完全由字母构成。
 *
 * @param message
 * @returns {*}
 */
export function alpha(message) {
  return attrs.pattern(/^[\w]+$/, message);
}

bindMessage(alpha, "%s必须是字母");

/**
 * 只能包含由字母、数字，以及 - 和 _
 *
 * @param message
 * @returns {*}
 */
export function alpha_dash(message) {
  return attrs.pattern(/^[\w\d_-]+$/, message);
}

bindMessage(alpha_dash, "%s只能包含由字母、数字，以及 - 和 _");

/**
 * 必须是完全是字母、数字
 *
 * @param message
 * @returns {*}
 */
export function alpha_num(message) {
  return attrs.pattern(/^[\w\d]+$/, message);
}

bindMessage(alpha_num, "%s只能包含字母、数字");
/**
 * 正确的金额
 *
 * @param message
 * @returns {*}
 */
export function num(message) {
  return attrs.pattern(
    /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/,
    message
  );
}

bindMessage(num, "%s格式不正确");

/**
 * 只能是汉字
 * @param message
 * @returns {*}
 */
export function chs(message) {
  return attrs.pattern(/^[\u4e00-\u9fa5]+$/, message);
}

bindMessage(chs, "%s只能是汉字");

/**
 * 只能包含汉字、字母
 * @param message
 * @returns {*}
 */
export function chs_alpha(message) {
  return attrs.pattern(/^[\u4e00-\u9fa5\w]+$/, message);
}

bindMessage(chs_alpha, "%s只能包含汉字、字母");

/**
 * 只能包含汉字、字母和数字
 * @param message
 * @returns {*}
 */
export function chs_alpha_num(message) {
  return attrs.pattern(/^[\u4e00-\u9fa5\w\d]+$/, message);
}

bindMessage(chs_alpha_num, "%s只能包含汉字、字母和数字");

/**
 * 只能包含由汉字、字母、数字，以及 - 和 _
 * @param message
 * @returns {*}
 */
export function chs_dash(message) {
  return attrs.pattern(/^[\u4e00-\u9fa5\w\d-_]+$/, message);
}

bindMessage(chs_dash, "%s只能包含由汉字、字母、数字，以及 - 和 _");

/**
 * 手机号验证
 * @param message
 * @returns {*}
 */
export function chs_phone(message) {
  return attrs.pattern(/^1(3|4|5|7|8|9|6)\d{9}$/i, message);
}
bindMessage(chs_phone, "请输入正确的手机号码");

const baseAttr = {
  min: "%s最小长度为:min",
  max: "%s最大长度为:max",
  length: "%s长度必须为:length",
  range: "%s长度为:range",
  pattern: "$s格式错误"
};

const attrs = Object.keys(baseAttr).reduce((attrs, key) => {
  attrs[key] = (attr, message = "", opt = {}) => {
    const _attr =
      key === "range" ? { min: attr[0], max: attr[1] } : { [key]: attr };

    return {
      message: message.replace(
        `:${key}`,
        key === "range" ? `${attr[0]}-${attr[1]}` : attr
      ),
      type: "string",
      ..._attr,
      ...opt
    };
  };
  bindMessage(attrs[key], baseAttr[key]);
  return attrs;
}, {});

export default attrs;
