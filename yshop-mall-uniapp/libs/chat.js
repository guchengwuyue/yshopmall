import $store from "@//store";
import { VUE_APP_WS_URL } from "@/utils";

const Socket = function() {
  this.ws = new WebSocket(VUE_APP_WS_URL);
  this.ws.onopen = this.onOpen.bind(this);
  this.ws.onerror = this.onError.bind(this);
  this.ws.onmessage = this.onMessage.bind(this);
  this.ws.onclose = this.onClose.bind(this);
};

Socket.prototype = {
  vm(vm) {
    this.vm = vm;
  },
  close() {
    clearInterval(this.timer);
    this.ws.close();
  },
  onOpen: function() {
    this.init();
    this.send({
      type: "login",
      data: $store.state.token
    });
    this.vm.$emit("socket_open");
  },
  init: function() {
    var that = this;
    this.timer = setInterval(function() {
      that.send({ type: "ping" });
    }, 10000);
  },
  send: function(data) {
    return this.ws.send(JSON.stringify(data));
  },
  onMessage: function(res) {
    const { type, data = {} } = JSON.parse(res.data);
    this.vm.$emit(type, data);
  },
  onClose: function() {
    clearInterval(this.timer);
  },
  onError: function(e) {
    this.vm.$emit("socket_error", e);
  }
};

Socket.prototype.constructor = Socket;

export default Socket;
