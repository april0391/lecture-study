import { useEffect, useState } from "react";
import axios from "axios";
import { BACKEND_URL } from "../../config/constants";

function Subscription({ userTo, userFrom }) {
  const [subscriberNumber, setSubscriberNumber] = useState(0);
  const [subscribed, setSubscribed] = useState(false);

  const payload = { userTo, userFrom };
  useEffect(() => {
    axios
      .post(`${BACKEND_URL}/api/subscribe/subscribeNumber`, payload)
      .then((res) => {
        if (res.data) {
          setSubscriberNumber(res.data.subscriberNumber);
        } else {
          alert("Failed to get subscriber Number");
        }
      });

    axios
      .post(`${BACKEND_URL}/api/subscribe/subscribed`, payload)
      .then((res) => {
        if (res.data) {
          setSubscribed(res.data.isSubscribed);
        } else {
          alert("Failed to get Subscribed Information");
        }
      });
  }, []);

  const onSubscribe = () => {
    if (subscribed) {
      axios
        .post(`${BACKEND_URL}/api/subscribe/unSubscribe`, payload)
        .then((res) => {
          if (res.data) {
            setSubscriberNumber(subscriberNumber - 1);
            setSubscribed(!subscribed);
          } else {
            alert("Failed to unsubscribe");
          }
        });
    } else {
      axios
        .post(`${BACKEND_URL}/api/subscribe/subscribe`, payload)
        .then((res) => {
          if (res.data) {
            setSubscriberNumber(subscriberNumber + 1);
            setSubscribed(!subscribed);
          } else {
            alert("Failed to subscribe");
          }
        });
    }
  };

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
