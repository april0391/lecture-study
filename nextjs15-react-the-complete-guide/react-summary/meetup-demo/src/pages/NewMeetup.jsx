import { useNavigate } from "react-router-dom";
import NewMeetupForm from "../components/meetups/NewMeetupForm";

const NewMeetup = () => {
  const navigate = useNavigate();
  const addMeetupHandler = (meetupData) => {
    console.log(meetupData);
    navigate("/");
  };

  return (
    <section>
      <h1>Add New Peetup</h1>
      <NewMeetupForm onAddMeetup={addMeetupHandler} />
    </section>
  );
};

export default NewMeetup;
