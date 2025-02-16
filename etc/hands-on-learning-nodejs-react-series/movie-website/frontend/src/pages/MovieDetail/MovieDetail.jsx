import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import Comments from "./Sections/Comments";
import LikeDislikes from "./Sections/LikeDislikes";
import {
  API_URL,
  API_KEY,
  IMAGE_BASE_URL,
  IMAGE_SIZE,
  BACKEND_URL,
} from "../../config/constants";
import GridCards from "../commons/GridCards";
import MainImage from "../landing/Sections/MainImage";
import MovieInfo from "./Sections/MovieInfo";
import Favorite from "./Sections/Favorite";

function MovieDetailPage(props) {
  const { id: movieId } = useParams();

  const [Movie, setMovie] = useState([]);
  const [Casts, setCasts] = useState([]);
  const [CommentLists, setCommentLists] = useState([]);
  const [LoadingForMovie, setLoadingForMovie] = useState(true);
  const [LoadingForCasts, setLoadingForCasts] = useState(true);
  const [ActorToggle, setActorToggle] = useState(false);
  const movieVariable = {
    movieId: movieId,
  };

  useEffect(() => {
    let endpointForMovieInfo = `${API_URL}movie/${movieId}?api_key=${API_KEY}&language=en-US`;
    fetchDetailInfo(endpointForMovieInfo);

    axios
      .post(`${BACKEND_URL}/api/comment/getComments`, movieVariable)
      .then((res) => {
        if (res.data.success) {
          setCommentLists(res.data.comments);
        } else {
          alert("Failed to get comments Info");
        }
      });
  }, []);

  const toggleActorView = () => {
    setActorToggle(!ActorToggle);
  };

  const fetchDetailInfo = (endpoint) => {
    fetch(endpoint)
      .then((result) => result.json())
      .then((result) => {
        setMovie(result);
        setLoadingForMovie(false);

        let endpointForCasts = `${API_URL}movie/${movieId}/credits?api_key=${API_KEY}`;
        fetch(endpointForCasts)
          .then((result) => result.json())
          .then((result) => {
            setCasts(result.cast);
          });

        setLoadingForCasts(false);
      })
      .catch((error) => console.error("Error:", error));
  };

  const updateComment = (newComment) => {
    setCommentLists(CommentLists.concat(newComment));
  };

  return (
    <div>
      {/* Header */}
      {!LoadingForMovie ? (
        <MainImage
          image={`${IMAGE_BASE_URL}${IMAGE_SIZE}${Movie.backdrop_path}`}
          title={Movie.original_title}
          text={Movie.overview}
        />
      ) : (
        <div>loading...</div>
      )}

      {/* Body */}
      <div className="w-4/5 mx-auto">
        <div className="flex justify-end">
          <Favorite
            movieInfo={Movie}
            movieId={movieId}
            userFrom={localStorage.getItem("userId")}
          />
        </div>

        {/* Movie Info */}
        {!LoadingForMovie ? <MovieInfo movie={Movie} /> : <div>loading...</div>}

        <br />
        {/* Actors Grid */}

        <div className="flex justify-center my-8">
          <button
            className="bg-blue-500 text-white px-6 py-2 rounded-md"
            onClick={toggleActorView}
          >
            Toggle Actor View
          </button>
        </div>

        {ActorToggle && (
          <div className="grid grid-cols-3 gap-8">
            {!LoadingForCasts ? (
              Casts.map(
                (cast, index) =>
                  cast.profile_path && (
                    <GridCards
                      key={index}
                      image={
                        cast.profile_path
                          ? `${IMAGE_BASE_URL}w500${cast.profile_path}`
                          : null
                      }
                      characterName={cast.name}
                    />
                  )
              )
            ) : (
              <div>loading...</div>
            )}
          </div>
        )}
        <br />

        <div className="flex justify-center">
          <LikeDislikes
            video
            videoId={movieId}
            userId={localStorage.getItem("userId")}
          />
        </div>

        {/* Comments */}
        <Comments
          movieTitle={Movie.original_title}
          CommentLists={CommentLists}
          postId={movieId}
          refreshFunction={updateComment}
        />
      </div>
    </div>
  );
}

export default MovieDetailPage;
