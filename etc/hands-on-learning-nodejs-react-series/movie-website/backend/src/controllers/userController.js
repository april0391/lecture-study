const userService = require("../services/userService");

const findAll = async (req, res) => {
  const users = await userService.findAll();
  return res.json(res);
};

const findById = async (req, res) => {};

const save = async (req, res) => {};

const updateById = async (req, res) => {};

const deleteById = async (req, res) => {};

module.exports = { findAll, save, findById, updateById, deleteById };
