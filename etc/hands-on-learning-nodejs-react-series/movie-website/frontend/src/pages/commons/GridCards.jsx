function GridCards(props) {
  return (
    <div className="w-full h-auto mb-4">
      <div className="relative">
        {props.landingPage ? (
          <a href={`/movie/${props.movieId}`}>
            <img
              className="w-full h-80 object-contain rounded-lg"
              src={props.image}
              alt={props.movieName}
            />
            <p className="text-center">{props.movieName}</p>
          </a>
        ) : (
          <img
            className="w-full h-80 object-contain rounded-lg"
            src={props.image}
            alt={props.characterName}
          />
        )}
      </div>
    </div>
  );
}

export default GridCards;
