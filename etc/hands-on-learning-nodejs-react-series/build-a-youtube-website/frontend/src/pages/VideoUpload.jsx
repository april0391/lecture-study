import { useState, useEffect } from "react";
import { PlusOutlined as Icon } from "@ant-design/icons";
import Dropzone from "react-dropzone";
import axios from "axios";

const BACKEND_URL = import.meta.env.VITE_BACKEND_URL;

const Private = [
  { value: 0, label: "Private" },
  { value: 1, label: "Public" },
];

const Catogory = [
  { value: 0, label: "Film & Animation" },
  { value: 0, label: "Autos & Vehicles" },
  { value: 0, label: "Music" },
  { value: 0, label: "Pets & Animals" },
  { value: 0, label: "Sports" },
];

const VideoUpload = (props) => {
  const [user, setUser] = useState(null);

  const [title, setTitle] = useState("");
  const [Description, setDescription] = useState("");
  const [privacy, setPrivacy] = useState(0);
  const [Categories, setCategories] = useState("Film & Animation");
  const [FilePath, setFilePath] = useState("");
  const [Duration, setDuration] = useState("");
  const [Thumbnail, setThumbnail] = useState("");

  useEffect(() => {
    const savedUser = localStorage.getItem("user");
    if (savedUser) {
      setUser(JSON.parse(savedUser));
    }
  }, []);

  const handleChangeTitle = (event) => {
    setTitle(event.currentTarget.value);
  };

  const handleChangeDecsription = (event) => {
    setDescription(event.currentTarget.value);
  };

  const handleChangeOne = (event) => {
    setPrivacy(event.currentTarget.value);
  };

  const handleChangeTwo = (event) => {
    setCategories(event.currentTarget.value);
  };

  const onSubmit = (event) => {
    event.preventDefault();

    if (!user || !user.isAuth) {
      return alert("Please Log in First");
    }

    if (
      title === "" ||
      Description === "" ||
      Categories === "" ||
      FilePath === "" ||
      Duration === "" ||
      Thumbnail === ""
    ) {
      return alert("Please first fill all the fields");
    }

    const variables = {
      writer: user._id,
      title: title,
      description: Description,
      privacy: privacy,
      filePath: FilePath,
      category: Categories,
      duration: Duration,
      thumbnail: Thumbnail,
    };

    axios
      .post(`${BACKEND_URL}/api/video/uploadVideo`, variables)
      .then((response) => {
        if (response.data.success) {
          alert("video Uploaded Successfully");
          props.history.push("/");
        } else {
          alert("Failed to upload video");
        }
      });
  };

  const onDrop = (files) => {
    let formData = new FormData();
    console.log(files);
    formData.append("file", files[0]);

    axios.post(`${BACKEND_URL}/api/video/uploadfiles`, formData).then((res) => {
      if (res.data.success) {
        let variable = {
          filePath: res.data.filePath,
          fileName: res.data.fileName,
        };
        setFilePath(res.data.filePath);

        // generate thumbnail with this filepath!
        axios
          .post(`${BACKEND_URL}/api/video/thumbnail`, variable)
          .then((res) => {
            if (res.data.success) {
              setDuration(res.data.fileDuration);
              setThumbnail(res.data.thumbsFilePath);
            } else {
              alert("Failed to make the thumbnails");
            }
          });
      } else {
        alert("failed to save the video in server");
      }
    });
  };

  return (
    <div className="max-w-2xl mx-auto my-8">
      <div className="text-center mb-8">
        <h2 className="text-2xl font-semibold">Upload Video</h2>
      </div>

      <form onSubmit={onSubmit}>
        <div className="flex justify-between mb-5">
          <Dropzone onDrop={onDrop} multiple={false} maxSize={800000000}>
            {({ getRootProps, getInputProps }) => (
              <div
                className="w-72 h-60 border border-light-gray flex items-center justify-center"
                {...getRootProps()}
              >
                <input {...getInputProps()} />
                <Icon className="text-5xl" />
              </div>
            )}
          </Dropzone>

          {Thumbnail !== "" && (
            <div className="ml-5">
              <img
                src={`http://localhost:5000/${Thumbnail}`}
                alt="Thumbnail"
                className="w-24"
              />
            </div>
          )}
        </div>

        <div className="mb-5">
          <label className="block mb-2">Title</label>
          <input
            type="text"
            value={title}
            onChange={handleChangeTitle}
            className="w-full p-2 border border-gray-300 rounded-md"
          />
        </div>

        <div className="mb-5">
          <label className="block mb-2">Description</label>
          <textarea
            value={Description}
            onChange={handleChangeDecsription}
            className="w-full p-2 border border-gray-300 rounded-md"
          />
        </div>

        <div className="mb-5">
          <label className="block mb-2">Privacy</label>
          <select
            onChange={handleChangeOne}
            className="w-full p-2 border border-gray-300 rounded-md"
          >
            {Private.map((item, index) => (
              <option key={index} value={item.value}>
                {item.label}
              </option>
            ))}
          </select>
        </div>

        <div className="mb-5">
          <label className="block mb-2">Category</label>
          <select
            onChange={handleChangeTwo}
            className="w-full p-2 border border-gray-300 rounded-md"
          >
            {Catogory.map((item, index) => (
              <option key={index} value={item.label}>
                {item.label}
              </option>
            ))}
          </select>
        </div>

        <button
          type="submit"
          className="w-full bg-blue-500 text-white p-3 rounded-md"
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default VideoUpload;
