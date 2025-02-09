const router = require("express").Router();
const subscriptionController = require("../controllers/subscriptionController");

router.post("/subscribeNumber", subscriptionController.getSubscriberNumber);
router.post("/subscribed", subscriptionController.isSubscribed);
router.post("/subscribe", subscriptionController.subscribe);
router.post("/unsubscribe", subscriptionController.unSubscribe);

module.exports = router;
