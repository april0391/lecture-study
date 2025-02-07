import { useEffect, useState } from "react";
import axios from "axios";
import { BACKEND_URL } from "../../config/constants";

function Subscription(props) {
  const { userTo, userFrom } = props;

  const [subscriberNumber, setSubscriberNumber] = useState(0);
  const [subscribed, setSubscribed] = useState(false);

  const onSubscribe = () => {
    let subscribeVariables = {
      userTo,
      userFrom,
    };

    if (subscribed) {
      //when we are already subscribed
      axios
        .post(`${BACKEND_URL}/api/subscribe/unSubscribe`, subscribeVariables)
        .then((res) => {
          if (res.data) {
            setSubscriberNumber(subscriberNumber - 1);
            setSubscribed(!subscribed);
          } else {
            alert("Failed to unsubscribe");
          }
        });
    } else {
      // when we are not subscribed yet
      axios
        .post("/api/subscribe/subscribe", subscribeVariables)
        .then((response) => {
          if (response.data.success) {
            setSubscriberNumber(subscriberNumber + 1);
            setSubscribed(!subscribed);
          } else {
            alert("Failed to subscribe");
          }
        });
    }
  };

  useEffect(() => {
    const subscribeNumberVariables = { userTo: userTo, userFrom: userFrom };
    axios
      .post("/api/subscribe/subscribeNumber", subscribeNumberVariables)
      .then((response) => {
        if (response.data.success) {
          setSubscriberNumber(response.data.subscribeNumber);
        } else {
          alert("Failed to get subscriber Number");
        }
      });

    axios
      .post("/api/subscribe/subscribed", subscribeNumberVariables)
      .then((response) => {
        if (response.data.success) {
          setSubscribed(response.data.subcribed);
        } else {
          alert("Failed to get Subscribed Information");
        }
      });
  }, []);

  return (
    <div>
      <button
        onClick={onSubscribe}
        style={{
          backgroundColor: `${subscribed ? "#AAAAAA" : "#CC0000"}`,
          borderRadius: "4px",
          color: "white",
          padding: "10px 16px",
          fontWeight: "500",
          fontSize: "1rem",
          textTransform: "uppercase",
        }}
      >
        {subscriberNumber} {subscribed ? "Subscribed" : "Subscribe"}
      </button>
    </div>
  );
}

export default Subscription;
