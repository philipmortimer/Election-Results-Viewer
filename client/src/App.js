import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import React from 'react';

import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import WardList from './WardList';
import WardResults from './WardResults';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      wardSelected: ""
    }
    this.wardSelected=this.wardSelected.bind(this);
  }

  wardSelected(wardId) {
    this.setState({wardSelected: wardId});
  }
  
  render() {
    return (
      <div className="App">
        <Navbar bg="primary" variant="dark">
          <Container>
            <Navbar.Brand href="#home">Election Explorer</Navbar.Brand>
          </Container>
        </Navbar>
        <Container className="mt-2">
          <Row>
            <Col>
              <WardList wardResultsRequested={this.wardSelected}></WardList>
            </Col>
            <Col>
              {
                this.state.wardSelected === "" ? "" :
                <WardResults wardId={this.state.wardSelected} ></WardResults>
              }
            </Col>
          </Row>
        </Container>
      </div>
    )
  }
}

export default App;
