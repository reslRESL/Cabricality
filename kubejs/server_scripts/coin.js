function playsoundCoin(server, player, type) {
	if (type == 'silver') {
		server.runCommandSilent(`playsound minecraft:block.amethyst_cluster.break ambient @a ${player.x} ${player.y} ${player.z}`)
		server.runCommandSilent(`playsound minecraft:block.anvil.place ambient @a ${player.x} ${player.y} ${player.z} 0.7 1.7`)
		server.runCommandSilent(`playsound ad_astra:passing_spaceship ambient @a ${player.x} ${player.y} ${player.z} 0.3`)
	}

	if (type == 'gold') {
		server.runCommandSilent(`playsound minecraft:block.amethyst_block.break ambient @a ${player.x} ${player.y} ${player.z}`)
		server.runCommandSilent(`playsound minecraft:block.lodestone.place ambient @a ${player.x} ${player.y} ${player.z}`)
		server.runCommandSilent(`playsound ad_astra:passing_spaceship ambient @a ${player.x} ${player.y} ${player.z} 0.3`)
	}
}

onEvent('item.right_click', event => {
	let server = event.server
	let player = event.getEntity()

	if (event.getItem() == 'cabricality:silver_coin'
	&& !randomEventCooling.includes(player.toString())) {
		randomEventCooling.push(player.toString())

		playsoundCoin(server, player, 'silver')

		server.scheduleInTicks(coinCoolingConst, server, () => {
			randomEventCooling = randomEventCooling.filter(function(item) { return item != player.toString() })
		})
	}

	if (event.getItem() == 'cabricality:gold_coin'
	&& !randomEventCooling.includes(player.toString())) {
		randomEventCooling.push(player.toString())

		playsoundCoin(server, player, 'gold')

		server.scheduleInTicks(coinCoolingConst, server, () => {
			randomEventCooling = randomEventCooling.filter(function(item) { return item != player.toString() })
		})
	}
})