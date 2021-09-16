
import VueAxios from 'vue-axios'
import axios from 'axios'
import store from '../store/index.js'
import { createToast } from 'mosha-vue-toastify'

const BASE_URL = "";
const DEFAULT_ACCEPT_TYPE = "application/json";

axios.defaults.baseURL = BASE_URL
axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE
axios.defaults.withCredentials = true;
