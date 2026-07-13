//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：div(arg1,arg2)
//返回值：arg1除以arg2的精确结果
export function div(arg1, arg2) {
  var t1 = 0,
    t2 = 0,
    r1,
    r2;
  try {
    t1 = arg1.toString().split(".")[1].length;
  } catch (e) {
    t1 = 0;
  }
  try {
    t2 = arg2.toString().split(".")[1].length;
  } catch (e) {
    t2 = 0;
  }
  r1 = Number(arg1.toString().replace(".", ""));
  r2 = Number(arg2.toString().replace(".", ""));
  return mul(r1 / r2, Math.pow(10, t2 - t1));
}
//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：mul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果
export function mul(arg1, arg2) {
  var m = 0,
    s1 = arg1.toString(),
    s2 = arg2.toString();
  try {
    m += s1.split(".")[1].length;
  } catch (e) {
    m = 0;
  }
  try {
    m += s2.split(".")[1].length;
  } catch (e) {
    m = m || 0;
  }
  return (
    (Number(s1.replace(".", "")) * Number(s2.replace(".", ""))) /
    Math.pow(10, m)
  );
}

//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：add(arg1,arg2)
//返回值：arg1加上arg2的精确结果
export function add(arg1, arg2) {
  var r1, r2, m, n;
  try {
    r1 = arg1.toString().split(".")[1].length;
  } catch (e) {
    r1 = 0;
  }
  try {
    r2 = arg2.toString().split(".")[1].length;
  } catch (e) {
    r2 = 0;
  }
  m = Math.pow(10, Math.max(r1, r2));
  n = r1 >= r2 ? r1 : r2;
  return ((arg1 * m + arg2 * m) / m).toFixed(n);
}

//减法函数，用来得到精确的减法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的减法结果。
//调用：sub(arg1,arg2)
//返回值：arg1减去arg2的精确结果
export function sub(arg1, arg2) {
  var r1, r2, m, n;
  try {
    r1 = arg1.toString().split(".")[1].length;
  } catch (e) {
    r1 = 0;
  }
  try {
    r2 = arg2.toString().split(".")[1].length;
  } catch (e) {
    r2 = 0;
  }
  m = Math.pow(10, Math.max(r1, r2));
  //动态控制精度长度
  n = r1 >= r2 ? r1 : r2;
  return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

function Compute(value) {
  this.value = value;
}
Object.assign(Compute.prototype, {
  add(v) {
    this.value = add(this.value, v);
    return this;
  },
  sub(v) {
    this.value = sub(this.value, v);
    return this;
  },
  div(v) {
    this.value = div(this.value, v);
    return this;
  },
  mul(v) {
    this.value = mul(this.value, v);
    return this;
  }
});

export default function(value) {
  return new Compute(value);
}
