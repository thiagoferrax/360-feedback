import React, { Component } from 'react';
import { Button, Card, CardBody, CardHeader, Col, Row, Table } from 'reactstrap';

import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import { getCompanies } from '../Company/CompanyActions'
import './Company.css'

import $ from 'jquery'
import 'bootstrap';

import Form from './CompanyForm'
import CompanyModal from './CompanyModal'

class Company extends Component {

  getFormatedDate(isoDate) {
    const options = { year: 'numeric', month: 'numeric', day: 'numeric' }

    const date = new Date(isoDate)
    return `${date.toLocaleDateString('en-US', options)} at ${date.toLocaleTimeString('en-US')}`
  }

  UNSAFE_componentWillMount() {
    this.props.getCompanies()
  }

  openNewCompanyModal() {
    $('#companyCreateModal').modal('show')
  }

  getTable() {
    const companies = this.props.companies
    const rowData = companies.map(company => (
      <tr key={company.id}>
        <td>{company.id}</td>
        <td>{company.name}</td>
        <td>{company.type}</td>
        <td>{this.getFormatedDate(company.createdAt)}</td>
        <td>
          <Button color="secondary"><i className="nav-icon icon-pencil"></i></Button>&nbsp;
          <Button color="danger"><i className="nav-icon icon-trash"></i></Button>
        </td>
      </tr>
    ))

    return (
    <Table responsive striped>
      <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Type</th>
          <th>Created At</th>
          <th>Actions</th>
        </tr>
      </thead><tbody>{rowData}</tbody>
    </Table>)
  }

  render() {

    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" lg="12">
            <Card>
              <CardHeader className="lineHeight">
                <i className="fa fa-align-justify"></i> Companies
                    <Button color="primary" className="alignRight" onClick={this.openNewCompanyModal}><i className="nav-icon icon-plus"></i></Button>
              </CardHeader>
              <CardBody>
                {this.getTable()}
              </CardBody>
            </Card>
          </Col>
        </Row>
        <Row>
          {/* Modal */}
          <CompanyModal id='companyCreateModal' title='Company' subtitle='Create'>
            <Form companies={this.props.companies} />
          </CompanyModal>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = state => ({ companies: state.company.companies })
const mapDispatchToProps = dispatch => bindActionCreators({ getCompanies }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(Company)
