import {combineReducers} from 'redux'

import DashboardReducer from '../views/Dashboard/DashboardReducer'

const rootReducer = combineReducers({
    dashboard: DashboardReducer
})

export default rootReducer