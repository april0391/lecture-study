const userService = require("../services/userService");

const findAll = async (req, res) => {
  const users = await userService.findAll();
  return res.json(users);
};

const findById = async (req, res) => {
  const user = await userService.findById(req.params.id);
  res.json(user);
};

const save = async (req, res) => {
  const user = await userService.save(req.body);
  res.json(user);
};

const updateById = async (req, res) => {
  const user = await userService.updateById(req.params.id, req.body);
  return res.json(user);
};

const deleteById = async (req, res) => {
  const user = await userService.deleteById(req.params.id);
  return res.json(user);
};

module.exports = { findAll, save, findById, updateById, deleteById };
