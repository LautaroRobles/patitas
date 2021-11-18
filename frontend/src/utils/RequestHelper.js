import axios from 'axios'

let server = process.env.VUE_APP_API_URL;

export default {
    get(params) {
        axios.get(server + params.url, params.config)
        .then(response => statusHandler(response, params.handler))
        .catch(error => errorHandler(error, params.handler))
        .then(response => alwaysHandler(response, params.handler))
    },
    post(params) {
        axios.post(server + params.url, params.body, params.config)
        .then(response => statusHandler(response, params.handler))
        .catch(error => errorHandler(error, params.handler))
        .then(response => alwaysHandler(response, params.handler))
    },
    put(params) {
        axios.put(server + params.url, params.body, params.config)
        .then(response => statusHandler(response, params.handler))
        .catch(error => errorHandler(error, params.handler))
        .then(response => alwaysHandler(response, params.handler))
    },
    delete(params) {
        axios.delete(server + params.url, params.config)
        .then(response => statusHandler(response, params.handler))
        .catch(error => errorHandler(error, params.handler))
        .then(response => alwaysHandler(response, params.handler))
    }
}

function statusHandler(response, handler) {

    if(handler[response.status])
        handler[response.status](response);
    else if(handler.default)
        handler.default(response);

}
function errorHandler(error, handler) {

    let response = error.response;

    if(response && handler[response.status])
        handler[response.status](response);
    else if(handler.error)
        handler.error(error);
}
function alwaysHandler(response, handler) {
    if(handler.always)
        handler.always(response)
}
