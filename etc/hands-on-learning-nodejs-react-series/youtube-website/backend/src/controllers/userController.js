const userService = require("../services/userService");

const findAll = async (req, res) => {
  const users = await userService.findAll();
  res.json(users);
};

const save = async (req, res) => {
  const user = await userService.save(req.body);
  res.status(201).json(user);
};

const findById = async (req, res) => {
  const user = await userService.findById(req.params.id);
  return res.json(user);
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
