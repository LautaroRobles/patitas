module.exports = {
	transpileDependencies: [
		'vuetify'
	],
	devServer: {
		port: 8081,
		proxy: {
			'/api': {
				target: 'http://patitas.ddns.net:8080',
				ws: true,
				changeOrigin: true
			}
		}
	},
}
