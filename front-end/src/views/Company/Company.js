import React, { Component } from 'react';
import { Button, Badge, Card, CardBody, CardHeader, Col, Pagination, PaginationItem, PaginationLink, Row, Table } from 'reactstrap';

import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import { getCompanies } from '../Company/CompanyActions'
import './Company.css'

import $ from 'jquery'
import 'bootstrap';

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
    $('#exampleModal').modal('show')
  }

  getRowData() {

    const companies = this.props.companies

    const rowData = companies.map(company => (
      <tr key="${company.id}">
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

    return (<tbody>{rowData}</tbody>)
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
                <Table responsive striped>
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Name</th>
                      <th>Type</th>
                      <th>Created At</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  {this.getRowData()}
                </Table>
                {/* <Pagination>
                  <PaginationItem disabled><PaginationLink previous tag="button">Prev</PaginationLink></PaginationItem>
                  <PaginationItem active>
                    <PaginationLink tag="button">1</PaginationLink>
                  </PaginationItem>
                  <PaginationItem><PaginationLink tag="button">2</PaginationLink></PaginationItem>
                  <PaginationItem><PaginationLink tag="button">3</PaginationLink></PaginationItem>
                  <PaginationItem><PaginationLink tag="button">4</PaginationLink></PaginationItem>
                  <PaginationItem><PaginationLink next tag="button">Next</PaginationLink></PaginationItem>
                </Pagination> */}
              </CardBody>
            </Card>
          </Col>
        </Row>
        <Row>
          <div>
            {/* Modal */}
            <div className="modal fade" id="exampleModal" tabIndex={-1} role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div className="modal-dialog" role="document">
                <div className="modal-content">
                  <div className="modal-header">
                    <h5 className="modal-title" id="exampleModalLabel">New Company</h5>
                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">×</span>
                    </button>
                  </div>
                  <div className="modal-body">
                    <form>
                      <div className="form-group">
                        <label htmlFor="recipient-name" className="form-control-label">Name:</label>
                        <input type="text" className="form-control" id="recipient-name" />
                      </div>
                      <div className="form-group">
                        <label htmlFor="message-text" className="form-control-label">Type:</label>
                        <select class="form-control" id="message-text">
                          <option value="2014">Head Office</option>
                          <option value="2015">Branch</option>
                        </select>
                      </div>
                    </form>
                  </div>
                  <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" className="btn btn-primary">Save</button>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </Row>

      </div>

    );
  }
}

const mapStateToProps = state => ({ companies: state.company.companies })
const mapDispatchToProps = dispatch => bindActionCreators({ getCompanies }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(Company)
