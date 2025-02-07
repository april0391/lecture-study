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
  const [description, setDescription] = useState("");
  const [privacy, setPrivacy] = useState(0);
  const [categories, setCategories] = useState("Film & Animation");
  const [filePath, setFilePath] = useState("");
  const [duration, setDuration] = useState("");
  const [thumbnail, setThumbnail] = useState("");

  useEffect(() => {
    const savedUser = localStorage.getItem("token");
    if (savedUser) {
      console.log(savedUser);
      setUser(savedUser);
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
      description === "" ||
      categories === "" ||
      filePath === "" ||
      duration === "" ||
      thumbnail === ""
    ) {
      return alert("Please first fill all the fields");
    }

    const variables = {
      writer: user._id,
      title: title,
      description: description,
      privacy: privacy,
      filePath: filePath,
      category: categories,
      duration: duration,
      thumbnail: thumbnail,
    };
  };

  const uploadVideo = async (formData) => {
    try {
      return await axios.post(`${BACKEND_URL}/api/video/uploadVideo`, formData);
    } catch (error) {
      console.error(error);
      alert("Failed to upload video.");
    }
  };

  const makeThumbnail = async (variables) => {
    try {
      return await axios.post(`${BACKEND_URL}/api/video/thumbnail`, variables);
    } catch (error) {
      console.error(error);
      alert("Failed to make the thumbnails");
    }
  };

  const onDrop = async (files) => {
    let formData = new FormData();
    formData.append("file", files[0]);

    const uploadResponse = await uploadVideo(formData);
    console.log(uploadResponse);

    let variables = {
      filePath: uploadResponse.data.filePath,
      fileName: uploadResponse.data.fileName,
    };
    setFilePath(uploadResponse.data.filePath);

    const thumbnailResponse = await makeThumbnail(variables);

    setDuration(thumbnailResponse.data.fileDuration);
    setThumbnail(thumbnailResponse.data.url);
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

          {thumbnail !== "" && (
            <div className="ml-5">
              <img
                src={`${BACKEND_URL}/${thumbnail}`}
                alt="Thumbnail"
                className="w-24"
              />
              <div>{Math.round(duration)}초</div>
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
            value={description}
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
