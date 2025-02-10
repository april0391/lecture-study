import React from "react";
import { Layout, Menu, Card, Row, Col, Statistic } from "antd";
import {
  UserOutlined,
  LaptopOutlined,
  NotificationOutlined,
} from "@ant-design/icons";

const { Header, Content, Sider } = Layout;

const Dashboard = () => {
  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Header className="header" style={{ background: "#001529", padding: 0 }}>
        <div style={{ color: "#fff", fontSize: "20px", paddingLeft: "20px" }}>
          My Dashboard
        </div>
      </Header>
      <Layout>
        <Sider width={200} className="site-layout-background">
          <Menu
            mode="inline"
            defaultSelectedKeys={["1"]}
            style={{ height: "100%", borderRight: 0 }}
          >
            <Menu.Item key="1" icon={<UserOutlined />}>
              Dashboard
            </Menu.Item>
            <Menu.Item key="2" icon={<LaptopOutlined />}>
              Devices
            </Menu.Item>
            <Menu.Item key="3" icon={<NotificationOutlined />}>
              Notifications
            </Menu.Item>
          </Menu>
        </Sider>
        <Layout style={{ padding: "0 24px 24px" }}>
          <Content
            style={{
              padding: 24,
              margin: 0,
              minHeight: 280,
            }}
          >
            <Row gutter={16}>
              <Col xs={24} sm={12} md={8} lg={8} xl={8}>
                <Card>
                  <Statistic
                    title="Total Users"
                    value={112893}
                    prefix={<UserOutlined />}
                  />
                </Card>
              </Col>
              <Col xs={24} sm={12} md={8} lg={8} xl={8}>
                <Card>
                  <Statistic
                    title="New Signups"
                    value={2334}
                    prefix={<LaptopOutlined />}
                  />
                </Card>
              </Col>
              <Col xs={24} sm={12} md={8} lg={8} xl={8}>
                <Card>
                  <Statistic
                    title="Notifications"
                    value={21}
                    prefix={<NotificationOutlined />}
                  />
                </Card>
              </Col>
            </Row>
            <Row gutter={16} style={{ marginTop: "20px" }}>
              <Col xs={24}>
                <Card title="Latest Updates" bordered={false}>
                  <p>Recent updates will be shown here...</p>
                </Card>
              </Col>
            </Row>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  );
};

export default Dashboard;
