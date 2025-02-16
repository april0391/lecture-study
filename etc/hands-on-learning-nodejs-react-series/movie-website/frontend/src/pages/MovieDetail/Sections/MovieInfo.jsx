function MovieInfo(props) {
  let { movie } = props;

  return (
    <div className="p-4 border border-gray-300 rounded-lg shadow-md">
      <h3 className="text-xl font-semibold mb-4">Movie Info</h3>
      <div className="grid grid-cols-2 gap-4">
        <div className="flex justify-between">
          <span className="font-medium">Title</span>
          <span>{movie.original_title}</span>
        </div>
        <div className="flex justify-between">
          <span className="font-medium">Release Date</span>
          <span>{movie.release_date}</span>
        </div>
        <div className="flex justify-between">
          <span className="font-medium">Revenue</span>
          <span>{movie.revenue}</span>
        </div>
        <div className="flex justify-between">
          <span className="font-medium">Runtime</span>
          <span>{movie.runtime} minutes</span>
        </div>
        <div className="flex justify-between col-span-2">
          <span className="font-medium">Vote Average</span>
          <span>{movie.vote_average}</span>
        </div>
        <div className="flex justify-between">
          <span className="font-medium">Vote Count</span>
          <span>{movie.vote_count}</span>
        </div>
        <div className="flex justify-between">
          <span className="font-medium">Status</span>
          <span>{movie.status}</span>
        </div>
        <div className="flex justify-between">
          <span className="font-medium">Popularity</span>
          <span>{movie.popularity}</span>
        </div>
      </div>
    </div>
  );
}

export default MovieInfo;
