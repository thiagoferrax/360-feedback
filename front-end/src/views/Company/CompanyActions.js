import axios from 'axios'
const BASE_URL = 'http://localhost:9090'

export function getCompanies() {
    const request = axios.get(`${BASE_URL}/companies/`)
    return {
        type: 'COMPANIES_FETCHED',
        payload: request
    }
}

export function showOrHideHeadOffices(event) {
    return {
        type: 'BRANCH_TYPE_SELECTED',
        payload: event.target.value === '2' 
    }   
}