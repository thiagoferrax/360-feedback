import axios from 'axios'
const BASE_URL = 'http://localhost:9090'

export function getSummary() {
    const request = axios.get(`${BASE_URL}/companies/1`)

    console.log(request)
    return {
        type: 'SUMMARY_FETCHED',
        payload: request
    }
}