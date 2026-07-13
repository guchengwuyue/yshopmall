function localStorage() {
  return window.localStorage;
}

function get(key) {
  return JSON.parse(localStorage().getItem(key));
}

function set(key, data) {
  return localStorage().setItem(key, JSON.stringify(data));
}

function all() {
  const data = {};
  for (var i = localStorage().length - 1; i >= 0; i--) {
    var key = localStorage().key(i);
    data[key] = get(key);
  }

  return data;
}

function remove(key) {
  return localStorage().removeItem(key);
}

function clearAll() {
  return localStorage().clear();
}

function has(key) {
  return localStorage().getItem(key) !== null;
}

export default {
  get,
  set,
  all,
  remove,
  clearAll,
  has
};
