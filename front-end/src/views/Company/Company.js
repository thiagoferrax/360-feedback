import React, { Component } from 'react';
import { Button, Badge, Card, CardBody, CardHeader, Col, Pagination, PaginationItem, PaginationLink, Row, Table } from 'reactstrap';

import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import { getCompanies } from '../Company/CompanyActions'
import './Company.css'

class Company extends Component {

  getFormatedDate(isoDate) {
    const options = { year: 'numeric', month: 'numeric', day: 'numeric' }

    const date = new Date(isoDate)
    return `${date.toLocaleDateString('en-US', options)} at ${date.toLocaleTimeString('en-US')}`
  }

  componentWillMount() {
    this.props.getCompanies()
  }

  getRowData() {

    const companies = this.props.companies

    const rowData = companies.map(company => (
      <tr>
        <td>{company.id}</td>
        <td>{company.name}</td>
        <td>{company.type}</td>
        <td>{this.getFormatedDate(company.createdAt)}</td>
        <td>
          <Button color="secondary"><i className="fa fa-edit"></i></Button>&nbsp;
          <Button color="danger"><i className="fa fa-trash"></i></Button>          
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
              <CardHeader>
                    <i className="fa fa-align-justify"></i> Companies
                    <Button color="primary"><i className="fa fa-plus"></i></Button>
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

      </div>

    );
  }
}

const mapStateToProps = state => ({ companies: state.company.companies })
const mapDispatchToProps = dispatch => bindActionCreators({ getCompanies }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(Company)
