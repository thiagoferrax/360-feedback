import axios from 'axios'
const BASE_URL = 'http://localhost:9090'

export function getCompanies() {
    const request = axios.get(`${BASE_URL}/companies/`)

    console.log(request)
    return {
        type: 'COMPANIES_FETCHED',
        payload: request
    }
}