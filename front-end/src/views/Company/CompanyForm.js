import React, { Component } from 'react';

import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import { showOrHideHeadOffices } from './CompanyActions'

class CompanyForm extends Component {

  getHeadOfficeCompanies() {

    if(!this.props.showHeadOffices) {
      return;
    }

    const headOfficeCompanies = this.props.companies.filter(company => company.type === 'HEAD_OFFICE')
    const options = headOfficeCompanies.map(company => (
      <option key={`options_${company.id}`} value={company}>{`${company.id} - ${company.name}`}</option>
    ))
    return (
      <div className="form-group">
        <div className="input-group">
          <div className="input-group-prepend">
            <span className="input-group-text">
              <i className="nav-icon icon-list" />
            </span>
          </div>
          <select className="form-control" id="headOffice">{options}</select>
        </div>
      </div>
    )
  }

  render() {

    return (
      <form>
        <div className="form-group">
          <div className="input-group">
            <div className="input-group-prepend">
              <span className="input-group-text">
                <i className="nav-icon icon-briefcase" />
              </span>
            </div>
            <input className="form-control" id="name" type="text" name="name" placeholder="Name" autoComplete="name" />
          </div>
        </div>
        <div className="form-group">
          <div className="input-group">
            <div className="input-group-prepend">
              <span className="input-group-text">
                <i className="nav-icon icon-list" />
              </span>
            </div>
            <select className="form-control" id="type" onChange={event => this.props.showOrHideHeadOffices(event)}>
              <option value="1">Head Office</option>
              <option value="2">Branch</option>
            </select>
          </div>
        </div>
        {this.getHeadOfficeCompanies()}
      </form>
    );
  }
}

const mapStateToProps = state => ({showHeadOffices: state.company.showHeadOffices})
const mapDispatchToProps = dispatch => bindActionCreators({showOrHideHeadOffices}, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(CompanyForm)
