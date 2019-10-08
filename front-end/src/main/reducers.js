import {combineReducers} from 'redux'

import DashboardReducer from '../views/Dashboard/DashboardReducer'
import CompanyReducer from '../views/Company/CompanyReducer'

const rootReducer = combineReducers({
    dashboard: DashboardReducer,
    company: CompanyReducer,
})

export default rootReducer