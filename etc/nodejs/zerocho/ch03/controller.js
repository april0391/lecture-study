const userService = require("./userService");
const { getPost, deletePost } = require("./postService");

userService.getUser();
userService.deleteUser();

getPost();
deletePost();

console.log(userService);
console.log(getPost);
console.log(deletePost);
