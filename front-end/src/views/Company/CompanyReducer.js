const INITIAL_STATE = { companies: [] }

export default function (state = INITIAL_STATE, action) {
    switch (action.type) {
        case 'COMPANIES_FETCHED':
            return { ...state, companies: action.payload.data }
        default:
            return state
    }
}