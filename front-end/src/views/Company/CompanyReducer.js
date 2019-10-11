const INITIAL_STATE = { companies: [], showHeadOffices: false }

export default function (state = INITIAL_STATE, action) {
    switch (action.type) {
        case 'COMPANIES_FETCHED':
            return { ...state, companies: action.payload.data }
        case 'BRANCH_TYPE_SELECTED':
            return { ...state, showHeadOffices: action.payload }
        default:
            return state
    }
}