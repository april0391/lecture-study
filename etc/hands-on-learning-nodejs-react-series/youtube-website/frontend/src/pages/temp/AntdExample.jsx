import React, { useState } from "react";
import { Button, Input, Form, message, Card } from "antd";

const AntdExample = () => {
  const [loading, setLoading] = useState(false);

  // 폼 제출 핸들러
  const onFinish = (values) => {
    setLoading(true);
    setTimeout(() => {
      message.success("로그인 성공!");
      setLoading(false);
    }, 2000);
  };

  return (
    <div
      style={{
        minHeight: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "#f0f2f5",
      }}
    >
      <Card
        style={{ width: 400, padding: "20px" }}
        title="로그인"
        bordered={false}
        headStyle={{ textAlign: "center", fontSize: "24px" }}
      >
        <Form
          name="login"
          initialValues={{ remember: true }}
          onFinish={onFinish}
          autoComplete="off"
        >
          <Form.Item
            label="아이디"
            name="username"
            rules={[{ required: true, message: "아이디를 입력하세요!" }]}
          >
            <Input placeholder="아이디" size="large" />
          </Form.Item>

          <Form.Item
            label="비밀번호"
            name="password"
            rules={[{ required: true, message: "비밀번호를 입력하세요!" }]}
          >
            <Input.Password placeholder="비밀번호" size="large" />
          </Form.Item>

          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              block
              size="large"
              loading={loading}
            >
              로그인
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};

export default AntdExample;
