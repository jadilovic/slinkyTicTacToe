if (process.env.NODE_ENV === "production") {
    const opt = require("./tic-tac-toe-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./tic-tac-toe-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./tic-tac-toe-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
