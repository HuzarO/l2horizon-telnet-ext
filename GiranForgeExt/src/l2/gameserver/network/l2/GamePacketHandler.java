/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.commons.net.nio.impl.IAcceptFilter
 *  l2.commons.net.nio.impl.IClientFactory
 *  l2.commons.net.nio.impl.IMMOExecutor
 *  l2.commons.net.nio.impl.IPacketHandler
 *  l2.commons.net.nio.impl.MMOConnection
 *  l2.commons.net.nio.impl.ReceivablePacket
 *  l2.gameserver.Config
 *  l2.gameserver.ThreadPoolManager
 *  l2.gameserver.network.l2.CGModule
 *  l2.gameserver.network.l2.GameClient
 *  l2.gameserver.network.l2.GameClient$GameClientState
 *  l2.gameserver.network.l2.c2s.Action
 *  l2.gameserver.network.l2.c2s.AddTradeItem
 *  l2.gameserver.network.l2.c2s.AnswerCoupleAction
 *  l2.gameserver.network.l2.c2s.AnswerJoinPartyRoom
 *  l2.gameserver.network.l2.c2s.AnswerPartyLootModification
 *  l2.gameserver.network.l2.c2s.AnswerTradeRequest
 *  l2.gameserver.network.l2.c2s.Appearing
 *  l2.gameserver.network.l2.c2s.AttackRequest
 *  l2.gameserver.network.l2.c2s.AuthLogin
 *  l2.gameserver.network.l2.c2s.BypassUserCmd
 *  l2.gameserver.network.l2.c2s.CannotMoveAnymore
 *  l2.gameserver.network.l2.c2s.CannotMoveAnymoreInVehicle
 *  l2.gameserver.network.l2.c2s.CharacterCreate
 *  l2.gameserver.network.l2.c2s.CharacterDelete
 *  l2.gameserver.network.l2.c2s.CharacterRestore
 *  l2.gameserver.network.l2.c2s.ConfirmDlg
 *  l2.gameserver.network.l2.c2s.DummyClientPacket
 *  l2.gameserver.network.l2.c2s.EnterWorld
 *  l2.gameserver.network.l2.c2s.ExPCCafeRequestOpenWindowWithoutNPC
 *  l2.gameserver.network.l2.c2s.ExSendClientINI
 *  l2.gameserver.network.l2.c2s.FinishRotatingC
 *  l2.gameserver.network.l2.c2s.GotoLobby
 *  l2.gameserver.network.l2.c2s.L2GameClientPacket
 *  l2.gameserver.network.l2.c2s.Logout
 *  l2.gameserver.network.l2.c2s.MoveBackwardToLocation
 *  l2.gameserver.network.l2.c2s.MoveWithDelta
 *  l2.gameserver.network.l2.c2s.NetPing
 *  l2.gameserver.network.l2.c2s.NewCharacter
 *  l2.gameserver.network.l2.c2s.NotifyStartMiniGame
 *  l2.gameserver.network.l2.c2s.PetitionVote
 *  l2.gameserver.network.l2.c2s.ProtocolVersion
 *  l2.gameserver.network.l2.c2s.ReplyGameGuardQuery
 *  l2.gameserver.network.l2.c2s.RequestActionUse
 *  l2.gameserver.network.l2.c2s.RequestAddExpandQuestAlarm
 *  l2.gameserver.network.l2.c2s.RequestAllAgitInfo
 *  l2.gameserver.network.l2.c2s.RequestAllCastleInfo
 *  l2.gameserver.network.l2.c2s.RequestAllFortressInfo
 *  l2.gameserver.network.l2.c2s.RequestAllyCrest
 *  l2.gameserver.network.l2.c2s.RequestAllyInfo
 *  l2.gameserver.network.l2.c2s.RequestAnswerJoinAlly
 *  l2.gameserver.network.l2.c2s.RequestAnswerJoinParty
 *  l2.gameserver.network.l2.c2s.RequestAnswerJoinPledge
 *  l2.gameserver.network.l2.c2s.RequestAquireSkill
 *  l2.gameserver.network.l2.c2s.RequestAquireSkillInfo
 *  l2.gameserver.network.l2.c2s.RequestAskJoinPartyRoom
 *  l2.gameserver.network.l2.c2s.RequestAutoSoulShot
 *  l2.gameserver.network.l2.c2s.RequestBBSwrite
 *  l2.gameserver.network.l2.c2s.RequestBidItemAuction
 *  l2.gameserver.network.l2.c2s.RequestBlock
 *  l2.gameserver.network.l2.c2s.RequestBlockMemoInfo
 *  l2.gameserver.network.l2.c2s.RequestBuyItem
 *  l2.gameserver.network.l2.c2s.RequestBuySeed
 *  l2.gameserver.network.l2.c2s.RequestCastleSiegeAttackerList
 *  l2.gameserver.network.l2.c2s.RequestCastleSiegeDefenderList
 *  l2.gameserver.network.l2.c2s.RequestChangeNicknameColor
 *  l2.gameserver.network.l2.c2s.RequestChangePetName
 *  l2.gameserver.network.l2.c2s.RequestCharacterNameCreatable
 *  l2.gameserver.network.l2.c2s.RequestClanAskJoinByName
 *  l2.gameserver.network.l2.c2s.RequestConfirmCancelItem
 *  l2.gameserver.network.l2.c2s.RequestConfirmCastleSiegeWaitingList
 *  l2.gameserver.network.l2.c2s.RequestConfirmGemStone
 *  l2.gameserver.network.l2.c2s.RequestConfirmRefinerItem
 *  l2.gameserver.network.l2.c2s.RequestConfirmTargetItem
 *  l2.gameserver.network.l2.c2s.RequestCreatePledge
 *  l2.gameserver.network.l2.c2s.RequestCrystallizeEstimate
 *  l2.gameserver.network.l2.c2s.RequestCrystallizeItem
 *  l2.gameserver.network.l2.c2s.RequestCrystallizeItemCancel
 *  l2.gameserver.network.l2.c2s.RequestCursedWeaponList
 *  l2.gameserver.network.l2.c2s.RequestCursedWeaponLocation
 *  l2.gameserver.network.l2.c2s.RequestDeleteBookMarkSlot
 *  l2.gameserver.network.l2.c2s.RequestDeleteMacro
 *  l2.gameserver.network.l2.c2s.RequestDestroyItem
 *  l2.gameserver.network.l2.c2s.RequestDismissAlly
 *  l2.gameserver.network.l2.c2s.RequestDismissParty
 *  l2.gameserver.network.l2.c2s.RequestDismissPartyRoom
 *  l2.gameserver.network.l2.c2s.RequestDispel
 *  l2.gameserver.network.l2.c2s.RequestDivideAdena
 *  l2.gameserver.network.l2.c2s.RequestDivideAdenaCancel
 *  l2.gameserver.network.l2.c2s.RequestDivideAdenaStart
 *  l2.gameserver.network.l2.c2s.RequestDropItem
 *  l2.gameserver.network.l2.c2s.RequestDuelAnswerStart
 *  l2.gameserver.network.l2.c2s.RequestDuelStart
 *  l2.gameserver.network.l2.c2s.RequestDuelSurrender
 *  l2.gameserver.network.l2.c2s.RequestEx2ndPasswordCheck
 *  l2.gameserver.network.l2.c2s.RequestEx2ndPasswordReq
 *  l2.gameserver.network.l2.c2s.RequestEx2ndPasswordVerify
 *  l2.gameserver.network.l2.c2s.RequestExAddEnchantScrollItem
 *  l2.gameserver.network.l2.c2s.RequestExAddPostFriendForPostBox
 *  l2.gameserver.network.l2.c2s.RequestExBR_BuyProduct
 *  l2.gameserver.network.l2.c2s.RequestExBR_EventRankerList
 *  l2.gameserver.network.l2.c2s.RequestExBR_GamePoint
 *  l2.gameserver.network.l2.c2s.RequestExBR_LectureMark
 *  l2.gameserver.network.l2.c2s.RequestExBR_MiniGameInsertScore
 *  l2.gameserver.network.l2.c2s.RequestExBR_MiniGameLoadScores
 *  l2.gameserver.network.l2.c2s.RequestExBR_ProductInfo
 *  l2.gameserver.network.l2.c2s.RequestExBR_ProductList
 *  l2.gameserver.network.l2.c2s.RequestExBR_RecentProductList
 *  l2.gameserver.network.l2.c2s.RequestExBuySellUIClose
 *  l2.gameserver.network.l2.c2s.RequestExCancelEnchantItem
 *  l2.gameserver.network.l2.c2s.RequestExCancelSentPost
 *  l2.gameserver.network.l2.c2s.RequestExChangeName
 *  l2.gameserver.network.l2.c2s.RequestExCleftEnter
 *  l2.gameserver.network.l2.c2s.RequestExCostumeChangeShortcut
 *  l2.gameserver.network.l2.c2s.RequestExCostumeCollectionSkillActive
 *  l2.gameserver.network.l2.c2s.RequestExCostumeEvolution
 *  l2.gameserver.network.l2.c2s.RequestExCostumeExtract
 *  l2.gameserver.network.l2.c2s.RequestExCostumeList
 *  l2.gameserver.network.l2.c2s.RequestExCostumeLock
 *  l2.gameserver.network.l2.c2s.RequestExCostumeUseItem
 *  l2.gameserver.network.l2.c2s.RequestExCubeGameChangeTeam
 *  l2.gameserver.network.l2.c2s.RequestExCubeGameReadyAnswer
 *  l2.gameserver.network.l2.c2s.RequestExDeletePostFriendForPostBox
 *  l2.gameserver.network.l2.c2s.RequestExDeleteReceivedPost
 *  l2.gameserver.network.l2.c2s.RequestExDeleteSentPost
 *  l2.gameserver.network.l2.c2s.RequestExDismissMpccRoom
 *  l2.gameserver.network.l2.c2s.RequestExDominionInfo
 *  l2.gameserver.network.l2.c2s.RequestExEnchantSkill
 *  l2.gameserver.network.l2.c2s.RequestExEndScenePlayer
 *  l2.gameserver.network.l2.c2s.RequestExEventMatchObserverEnd
 *  l2.gameserver.network.l2.c2s.RequestExFishRanking
 *  l2.gameserver.network.l2.c2s.RequestExFriendListForPostBox
 *  l2.gameserver.network.l2.c2s.RequestExItemEnsoul
 *  l2.gameserver.network.l2.c2s.RequestExJoinDominionWar
 *  l2.gameserver.network.l2.c2s.RequestExJoinMpccRoom
 *  l2.gameserver.network.l2.c2s.RequestExJump
 *  l2.gameserver.network.l2.c2s.RequestExListMpccWaiting
 *  l2.gameserver.network.l2.c2s.RequestExMPCCAcceptJoin
 *  l2.gameserver.network.l2.c2s.RequestExMPCCAskJoin
 *  l2.gameserver.network.l2.c2s.RequestExMPCCShowPartyMembersInfo
 *  l2.gameserver.network.l2.c2s.RequestExMagicSkillUseGround
 *  l2.gameserver.network.l2.c2s.RequestExManageMpccRoom
 *  l2.gameserver.network.l2.c2s.RequestExMoveToLocationAirShip
 *  l2.gameserver.network.l2.c2s.RequestExMoveToLocationInAirShip
 *  l2.gameserver.network.l2.c2s.RequestExMpccPartymasterList
 *  l2.gameserver.network.l2.c2s.RequestExOlympiadObserverEnd
 *  l2.gameserver.network.l2.c2s.RequestExOustFromMPCC
 *  l2.gameserver.network.l2.c2s.RequestExOustFromMpccRoom
 *  l2.gameserver.network.l2.c2s.RequestExPostItemList
 *  l2.gameserver.network.l2.c2s.RequestExReceivePost
 *  l2.gameserver.network.l2.c2s.RequestExRefundItem
 *  l2.gameserver.network.l2.c2s.RequestExRejectPost
 *  l2.gameserver.network.l2.c2s.RequestExRemoveEnchantSupportItem
 *  l2.gameserver.network.l2.c2s.RequestExRequestReceivedPost
 *  l2.gameserver.network.l2.c2s.RequestExRequestReceivedPostList
 *  l2.gameserver.network.l2.c2s.RequestExRequestSentPost
 *  l2.gameserver.network.l2.c2s.RequestExRequestSentPostList
 *  l2.gameserver.network.l2.c2s.RequestExRqItemLink
 *  l2.gameserver.network.l2.c2s.RequestExSeedPhase
 *  l2.gameserver.network.l2.c2s.RequestExSendPost
 *  l2.gameserver.network.l2.c2s.RequestExShowNewUserPetition
 *  l2.gameserver.network.l2.c2s.RequestExShowPostFriendListForPostBox
 *  l2.gameserver.network.l2.c2s.RequestExShowStepThree
 *  l2.gameserver.network.l2.c2s.RequestExShowStepTwo
 *  l2.gameserver.network.l2.c2s.RequestExStartShowCrataeCubeRank
 *  l2.gameserver.network.l2.c2s.RequestExStopShowCrataeCubeRank
 *  l2.gameserver.network.l2.c2s.RequestExTryToPutEnchantSupportItem
 *  l2.gameserver.network.l2.c2s.RequestExTryToPutEnchantTargetItem
 *  l2.gameserver.network.l2.c2s.RequestExWithdrawMpccRoom
 *  l2.gameserver.network.l2.c2s.RequestExitPartyMatchingWaitingRoom
 *  l2.gameserver.network.l2.c2s.RequestFortressMapInfo
 *  l2.gameserver.network.l2.c2s.RequestFortressSiegeInfo
 *  l2.gameserver.network.l2.c2s.RequestFriendAddReply
 *  l2.gameserver.network.l2.c2s.RequestFriendDel
 *  l2.gameserver.network.l2.c2s.RequestFriendDetailInfo
 *  l2.gameserver.network.l2.c2s.RequestFriendInfoList
 *  l2.gameserver.network.l2.c2s.RequestFriendInvite
 *  l2.gameserver.network.l2.c2s.RequestFriendList
 *  l2.gameserver.network.l2.c2s.RequestGMCommand
 *  l2.gameserver.network.l2.c2s.RequestGetBossRecord
 *  l2.gameserver.network.l2.c2s.RequestGetItemFromPet
 *  l2.gameserver.network.l2.c2s.RequestGetOffVehicle
 *  l2.gameserver.network.l2.c2s.RequestGetOnVehicle
 *  l2.gameserver.network.l2.c2s.RequestGiveItemToPet
 *  l2.gameserver.network.l2.c2s.RequestGiveNickName
 *  l2.gameserver.network.l2.c2s.RequestGmList
 *  l2.gameserver.network.l2.c2s.RequestGoodsInventoryInfo
 *  l2.gameserver.network.l2.c2s.RequestHandOverPartyMaster
 *  l2.gameserver.network.l2.c2s.RequestHardWareInfo
 *  l2.gameserver.network.l2.c2s.RequestHennaEquip
 *  l2.gameserver.network.l2.c2s.RequestHennaItemInfo
 *  l2.gameserver.network.l2.c2s.RequestHennaList
 *  l2.gameserver.network.l2.c2s.RequestHennaUnequip
 *  l2.gameserver.network.l2.c2s.RequestHennaUnequipInfo
 *  l2.gameserver.network.l2.c2s.RequestHennaUnequipList
 *  l2.gameserver.network.l2.c2s.RequestInfoItemAuction
 *  l2.gameserver.network.l2.c2s.RequestInzoneWaitingTime
 *  l2.gameserver.network.l2.c2s.RequestItemList
 *  l2.gameserver.network.l2.c2s.RequestJoinAlly
 *  l2.gameserver.network.l2.c2s.RequestJoinCastleSiege
 *  l2.gameserver.network.l2.c2s.RequestJoinParty
 *  l2.gameserver.network.l2.c2s.RequestJoinPledge
 *  l2.gameserver.network.l2.c2s.RequestKeyMapping
 *  l2.gameserver.network.l2.c2s.RequestLinkHtml
 *  l2.gameserver.network.l2.c2s.RequestListPartyMatchingWaitingRoom
 *  l2.gameserver.network.l2.c2s.RequestMagicSkillList
 *  l2.gameserver.network.l2.c2s.RequestMakeMacro
 *  l2.gameserver.network.l2.c2s.RequestManorList
 *  l2.gameserver.network.l2.c2s.RequestModifyBookMarkSlot
 *  l2.gameserver.network.l2.c2s.RequestMoveToLocationInVehicle
 *  l2.gameserver.network.l2.c2s.RequestMultiSellChoose
 *  l2.gameserver.network.l2.c2s.RequestNewEnchantPushOne
 *  l2.gameserver.network.l2.c2s.RequestNewEnchantPushTwo
 *  l2.gameserver.network.l2.c2s.RequestNewEnchantRemoveOne
 *  l2.gameserver.network.l2.c2s.RequestNewEnchantRemoveTwo
 *  l2.gameserver.network.l2.c2s.RequestNewEnchantRetryToPutItems
 *  l2.gameserver.network.l2.c2s.RequestNewEnchantTry
 *  l2.gameserver.network.l2.c2s.RequestObserverEnd
 *  l2.gameserver.network.l2.c2s.RequestOlympiadMatchList
 *  l2.gameserver.network.l2.c2s.RequestOlympiadObserverEnd
 *  l2.gameserver.network.l2.c2s.RequestOneDayRewardReceive
 *  l2.gameserver.network.l2.c2s.RequestOustAlly
 *  l2.gameserver.network.l2.c2s.RequestOustFromPartyRoom
 *  l2.gameserver.network.l2.c2s.RequestOustPartyMember
 *  l2.gameserver.network.l2.c2s.RequestOustPledgeMember
 *  l2.gameserver.network.l2.c2s.RequestPCCafeCouponUse
 *  l2.gameserver.network.l2.c2s.RequestPVPMatchRecord
 *  l2.gameserver.network.l2.c2s.RequestPackageSend
 *  l2.gameserver.network.l2.c2s.RequestPackageSendableItemList
 *  l2.gameserver.network.l2.c2s.RequestPartyLootModification
 *  l2.gameserver.network.l2.c2s.RequestPartyMatchConfig
 *  l2.gameserver.network.l2.c2s.RequestPartyMatchDetail
 *  l2.gameserver.network.l2.c2s.RequestPartyMatchList
 *  l2.gameserver.network.l2.c2s.RequestPetGetItem
 *  l2.gameserver.network.l2.c2s.RequestPetUseItem
 *  l2.gameserver.network.l2.c2s.RequestPetition
 *  l2.gameserver.network.l2.c2s.RequestPetitionCancel
 *  l2.gameserver.network.l2.c2s.RequestPledgeCrest
 *  l2.gameserver.network.l2.c2s.RequestPledgeDraftListApply
 *  l2.gameserver.network.l2.c2s.RequestPledgeDraftListSearch
 *  l2.gameserver.network.l2.c2s.RequestPledgeEmblem
 *  l2.gameserver.network.l2.c2s.RequestPledgeExtendedInfo
 *  l2.gameserver.network.l2.c2s.RequestPledgeInfo
 *  l2.gameserver.network.l2.c2s.RequestPledgeMemberInfo
 *  l2.gameserver.network.l2.c2s.RequestPledgeMemberList
 *  l2.gameserver.network.l2.c2s.RequestPledgeMemberPowerInfo
 *  l2.gameserver.network.l2.c2s.RequestPledgePower
 *  l2.gameserver.network.l2.c2s.RequestPledgePowerGradeList
 *  l2.gameserver.network.l2.c2s.RequestPledgeRecruitApplyInfo
 *  l2.gameserver.network.l2.c2s.RequestPledgeRecruitBoardAccess
 *  l2.gameserver.network.l2.c2s.RequestPledgeRecruitBoardDetail
 *  l2.gameserver.network.l2.c2s.RequestPledgeRecruitBoardSearch
 *  l2.gameserver.network.l2.c2s.RequestPledgeRecruitInfo
 *  l2.gameserver.network.l2.c2s.RequestPledgeReorganizeMember
 *  l2.gameserver.network.l2.c2s.RequestPledgeSetAcademyMaster
 *  l2.gameserver.network.l2.c2s.RequestPledgeSetMemberPowerGrade
 *  l2.gameserver.network.l2.c2s.RequestPledgeSignInForOpenJoiningMethod
 *  l2.gameserver.network.l2.c2s.RequestPledgeWaitingApplied
 *  l2.gameserver.network.l2.c2s.RequestPledgeWaitingApply
 *  l2.gameserver.network.l2.c2s.RequestPledgeWaitingList
 *  l2.gameserver.network.l2.c2s.RequestPledgeWaitingUser
 *  l2.gameserver.network.l2.c2s.RequestPledgeWaitingUserAccept
 *  l2.gameserver.network.l2.c2s.RequestPledgeWarList
 *  l2.gameserver.network.l2.c2s.RequestPreviewItem
 *  l2.gameserver.network.l2.c2s.RequestPrivateStoreBuy
 *  l2.gameserver.network.l2.c2s.RequestPrivateStoreBuyManage
 *  l2.gameserver.network.l2.c2s.RequestPrivateStoreBuySellList
 *  l2.gameserver.network.l2.c2s.RequestPrivateStoreList
 *  l2.gameserver.network.l2.c2s.RequestPrivateStoreQuitBuy
 *  l2.gameserver.network.l2.c2s.RequestPrivateStoreQuitSell
 *  l2.gameserver.network.l2.c2s.RequestProcureCropList
 *  l2.gameserver.network.l2.c2s.RequestQuestAbort
 *  l2.gameserver.network.l2.c2s.RequestQuestList
 *  l2.gameserver.network.l2.c2s.RequestRaidBossSpawnInfo
 *  l2.gameserver.network.l2.c2s.RequestRecipeBookOpen
 *  l2.gameserver.network.l2.c2s.RequestRecipeItemDelete
 *  l2.gameserver.network.l2.c2s.RequestRecipeItemMakeInfo
 *  l2.gameserver.network.l2.c2s.RequestRecipeItemMakeSelf
 *  l2.gameserver.network.l2.c2s.RequestRecipeShopListSet
 *  l2.gameserver.network.l2.c2s.RequestRecipeShopMakeDo
 *  l2.gameserver.network.l2.c2s.RequestRecipeShopMakeInfo
 *  l2.gameserver.network.l2.c2s.RequestRecipeShopManageCancel
 *  l2.gameserver.network.l2.c2s.RequestRecipeShopManageQuit
 *  l2.gameserver.network.l2.c2s.RequestRecipeShopMessageSet
 *  l2.gameserver.network.l2.c2s.RequestRecipeShopSellList
 *  l2.gameserver.network.l2.c2s.RequestRefine
 *  l2.gameserver.network.l2.c2s.RequestRefineCancel
 *  l2.gameserver.network.l2.c2s.RequestReload
 *  l2.gameserver.network.l2.c2s.RequestRemainTime
 *  l2.gameserver.network.l2.c2s.RequestResetNickname
 *  l2.gameserver.network.l2.c2s.RequestRestart
 *  l2.gameserver.network.l2.c2s.RequestRestartPoint
 *  l2.gameserver.network.l2.c2s.RequestSEKCustom
 *  l2.gameserver.network.l2.c2s.RequestSSQStatus
 *  l2.gameserver.network.l2.c2s.RequestSaveBookMarkSlot
 *  l2.gameserver.network.l2.c2s.RequestSaveInventoryOrder
 *  l2.gameserver.network.l2.c2s.RequestSaveKeyMapping
 *  l2.gameserver.network.l2.c2s.RequestSellItem
 *  l2.gameserver.network.l2.c2s.RequestSendL2FriendSay
 *  l2.gameserver.network.l2.c2s.RequestSendMsnChatLog
 *  l2.gameserver.network.l2.c2s.RequestSetAllyCrest
 *  l2.gameserver.network.l2.c2s.RequestSetCastleSiegeTime
 *  l2.gameserver.network.l2.c2s.RequestSetCrop
 *  l2.gameserver.network.l2.c2s.RequestSetPledgeCrest
 *  l2.gameserver.network.l2.c2s.RequestSetPledgeCrestLarge
 *  l2.gameserver.network.l2.c2s.RequestSetSeed
 *  l2.gameserver.network.l2.c2s.RequestShortCutDel
 *  l2.gameserver.network.l2.c2s.RequestShortCutReg
 *  l2.gameserver.network.l2.c2s.RequestShowBoard
 *  l2.gameserver.network.l2.c2s.RequestShowMiniMap
 *  l2.gameserver.network.l2.c2s.RequestSiegeInfo
 *  l2.gameserver.network.l2.c2s.RequestSkillCoolTime
 *  l2.gameserver.network.l2.c2s.RequestSkillList
 *  l2.gameserver.network.l2.c2s.RequestStartPledgeWar
 *  l2.gameserver.network.l2.c2s.RequestStopPledgeWar
 *  l2.gameserver.network.l2.c2s.RequestTargetCanceld
 *  l2.gameserver.network.l2.c2s.RequestTeleport
 *  l2.gameserver.network.l2.c2s.RequestTeleportBookMark
 *  l2.gameserver.network.l2.c2s.RequestTimeCheck
 *  l2.gameserver.network.l2.c2s.RequestTodoList
 *  l2.gameserver.network.l2.c2s.RequestTryEnSoulExtraction
 *  l2.gameserver.network.l2.c2s.RequestTutorialClientEvent
 *  l2.gameserver.network.l2.c2s.RequestTutorialLinkHtml
 *  l2.gameserver.network.l2.c2s.RequestTutorialPassCmdToServer
 *  l2.gameserver.network.l2.c2s.RequestTutorialQuestionMark
 *  l2.gameserver.network.l2.c2s.RequestUpdateBlockMemo
 *  l2.gameserver.network.l2.c2s.RequestUpdateFriendMemo
 *  l2.gameserver.network.l2.c2s.RequestUserBanInfo
 *  l2.gameserver.network.l2.c2s.RequestVipInfo
 *  l2.gameserver.network.l2.c2s.RequestVipProductList
 *  l2.gameserver.network.l2.c2s.RequestVoteNew
 *  l2.gameserver.network.l2.c2s.RequestWithDrawPremiumItem
 *  l2.gameserver.network.l2.c2s.RequestWithDrawalParty
 *  l2.gameserver.network.l2.c2s.RequestWithdrawAlly
 *  l2.gameserver.network.l2.c2s.RequestWithdrawPartyRoom
 *  l2.gameserver.network.l2.c2s.RequestWithdrawalPledge
 *  l2.gameserver.network.l2.c2s.RequestWriteHeroWords
 *  l2.gameserver.network.l2.c2s.Say2C
 *  l2.gameserver.network.l2.c2s.ScriptExPacket
 *  l2.gameserver.network.l2.c2s.ScriptPacket
 *  l2.gameserver.network.l2.c2s.SendBypassBuildCmd
 *  l2.gameserver.network.l2.c2s.SendWareHouseDepositList
 *  l2.gameserver.network.l2.c2s.SendWareHouseWithDrawList
 *  l2.gameserver.network.l2.c2s.SetPrivateStoreBuyList
 *  l2.gameserver.network.l2.c2s.SetPrivateStoreMsgBuy
 *  l2.gameserver.network.l2.c2s.SetPrivateStoreMsgSell
 *  l2.gameserver.network.l2.c2s.SetPrivateStoreSellList
 *  l2.gameserver.network.l2.c2s.SetPrivateStoreWholeMsg
 *  l2.gameserver.network.l2.c2s.SnoopQuit
 *  l2.gameserver.network.l2.c2s.StartRotatingC
 *  l2.gameserver.network.l2.c2s.TradeDone
 *  l2.gameserver.network.l2.c2s.TradeRequest
 *  l2.gameserver.network.l2.c2s.UseItem
 *  l2.gameserver.network.l2.c2s.ValidatePosition
 *  l2.gameserver.network.l2.s2c.RequestNewEnchantClose
 *  l2.gameserver.network.l2.s2c.RequestTargetActionMenu
 *  l2.gameserver.scripts.Scripts
 *  l2.gameserver.utils.Log
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package l2.gameserver.network.l2;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Config.GiranForgeConfig;
import l2.commons.net.nio.impl.IAcceptFilter;
import l2.commons.net.nio.impl.IClientFactory;
import l2.commons.net.nio.impl.IMMOExecutor;
import l2.commons.net.nio.impl.IPacketHandler;
import l2.commons.net.nio.impl.MMOConnection;
import l2.commons.net.nio.impl.ReceivablePacket;
import l2.gameserver.Config;
import l2.gameserver.ThreadPoolManager;
import l2.gameserver.network.l2.c2s.*;
import l2.gameserver.network.l2.c2s.appearance.RequestAppearanceCancel;
import l2.gameserver.network.l2.c2s.appearance.RequestAppearanceExtraction;
import l2.gameserver.network.l2.c2s.appearance.RequestAppearanceModify;
import l2.gameserver.network.l2.c2s.appearance.RequestAppearanceTarget;
import l2.gameserver.network.l2.c2s.upgrade.RequestNormalUpgradeSystem;
import l2.gameserver.network.l2.s2c.RequestNewEnchantClose;
import l2.gameserver.network.l2.s2c.RequestTargetActionMenu;
import l2.gameserver.network.l2.s2c.gf.RequestRaidServerInfo;
import l2.gameserver.scripts.Scripts;
import l2.gameserver.utils.Log;

public final class GamePacketHandler
implements IAcceptFilter,
IClientFactory<GameClient>,
IMMOExecutor<GameClient>,
IPacketHandler<GameClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GamePacketHandler.class);
    private static final Set<Integer> IGNORED_PACKETS = Set.of(Integer.valueOf(15), Integer.valueOf(89), Integer.valueOf(177), Integer.valueOf(208));
    private static final int OPCODE_EX = 208;
    private static final int OPCODE_MULTI = 74;
    private final Set<String> whitelistedIps = new CopyOnWriteArraySet<String>();

    private static String formatHexByte(int value) {
        return String.format("%02X", value & 0xFF);
    }

    private static String formatHexShort(int value) {
        return String.format("%04X", value & 0xFFFF);
    }

    private static String formatOpcodeHex(int opcode) {
        return Integer.toHexString(opcode).toUpperCase(Locale.US);
    }

    private static String formatOpcodeHex(int opcode, int exOpcode) {
        return GamePacketHandler.formatOpcodeHex(opcode) + ":" + GamePacketHandler.formatOpcodeHex(exOpcode);
    }

    public static String formatHexDump(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i += 16) {
            int j;
            sb.append(String.format("%04x: ", i));
            for (j = 0; j < 16; ++j) {
                if (i + j < array.length) {
                    sb.append(String.format("%02x ", array[i + j]));
                    continue;
                }
                sb.append("   ");
            }
            sb.append(": ");
            for (j = 0; j < 16; ++j) {
                if (i + j < array.length) {
                    char c = (char)array[i + j];
                    sb.append(c > '\u001f' && c < '\u007f' ? c : (char)'.');
                    continue;
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public ReceivablePacket<GameClient> handlePacket(ByteBuffer buffer, GameClient client) {
        int initialPosition = buffer.position();
        int opcode = buffer.get() & 0xFF;
        ReceivablePacket<GameClient> packet = null;
        if (!this.isIgnoredPacket(opcode) && GiranForgeConfig.PACKET_DEBUG_MODE) {
            System.out.print("\n[================== Packet Received ====================]\n");
            LOGGER.info("Opcode: 0x{} ({})", (Object)GamePacketHandler.formatHexByte(opcode), (Object)opcode);
            LOGGER.info("State: {}", (Object)client.getState());
            System.out.print("[=======================================================]\n");
        }
        if (CGModule.getInstance().isActive() && (packet = CGModule.getInstance().handlePacket(client, opcode)) != null) {
            if (!this.isIgnoredPacket(opcode) && GiranForgeConfig.PACKET_DEBUG_MODE) {
                this.logPacketCreated((ReceivablePacket<GameClient>)packet, client.getState(), opcode);
            }
            return packet;
        }
        if (!client.getPacketFilter().checkPacket(opcode)) {
            if (!this.isIgnoredPacket(opcode) && GiranForgeConfig.PACKET_DEBUG_MODE) {
                LOGGER.warn("Packet filtered out: 0x{} ({})", (Object)GamePacketHandler.formatHexByte(opcode), (Object)opcode);
            }
            return null;
        }
        try {
            packet = switch (client.getState()) {
                case CONNECTED -> this.handleConnectedState(opcode, buffer, client);
                case AUTHED -> this.handleAuthedState(opcode, buffer, client);
                case IN_GAME -> this.handleInGameState(opcode, buffer, client);
                default -> null;
            };
        }
        catch (BufferUnderflowException e) {
            client.onPacketReadFail();
        }
        if (packet != null && !this.isIgnoredPacket(opcode) && GiranForgeConfig.PACKET_DEBUG_MODE) {
            this.logPacketCreated((ReceivablePacket<GameClient>)packet, client.getState(), opcode);
        } else if (packet == null && GiranForgeConfig.PACKET_DEBUG_MODE) {
            this.logUnknownPacket(client, buffer, initialPosition, opcode);
        }
        return packet != null && client.getPacketFilter().checkPacket(packet) ? packet : null;
    }

    private boolean isIgnoredPacket(int opcode) {
        return IGNORED_PACKETS.contains(opcode);
    }

    private ReceivablePacket<GameClient> handleConnectedState(int opcode, ByteBuffer buffer, GameClient client) {
        return switch (opcode) {
            case 0 -> null;
            case 14 -> new ProtocolVersion();
            case 43 -> new AuthLogin();
            case 203 -> new ReplyGameGuardQuery();
            case 208 -> this.handleExPacket(buffer, client, ClientState.CONNECTED);
            default -> this.handleScriptPacket(client, opcode);
        };
    }

    private ReceivablePacket<GameClient> handleAuthedState(int opcode, ByteBuffer buffer, GameClient client) {
        return switch (opcode) {
            case 0 -> new Logout();
            case 12 -> new CharacterCreate();
            case 13 -> new CharacterDelete();
            case 18 -> new CharacterSelected();
            case 19 -> new NewCharacter();
            case 72, 106 -> DummyClientPacket.STATIC_DUMMY_CLIENT_PACKET;
            case 123 -> new CharacterRestore();
            case 169 -> new RequestBlock();
            case 177 -> new NetPing();
            case 203 -> new ReplyGameGuardQuery();
            case 208 -> this.handleExPacket(buffer, client, ClientState.AUTHED);
            default -> this.handleScriptPacket(client, opcode);
        };
    }

    private ReceivablePacket<GameClient> handleInGameState(int opcode, ByteBuffer buffer, GameClient client) {
        return switch (opcode) {
            case 0 -> new Logout();
            case 1 -> new AttackRequest();
            case 3 -> new RequestStartPledgeWar();
            case 5 -> new RequestStopPledgeWar();
            case 7, 203 -> new ReplyGameGuardQuery();
            case 9 -> new RequestSetPledgeCrest();
            case 11 -> new RequestGiveNickName();
            case 15 -> new MoveBackwardToLocation();
            case 17 -> new EnterWorld();
            case 20 -> new RequestItemList();
            case 23 -> new RequestDropItem();
            case 25 -> new UseItem();
            case 26 -> new TradeRequest();
            case 27 -> new AddTradeItem();
            case 28 -> new TradeDone();
            case 31 -> new Action();
            case 34 -> new RequestLinkHtml();
            case 35 -> new RequestBypassToServer();
            case 36 -> new RequestBBSwrite();
            case 37 -> new RequestCreatePledge();
            case 38 -> new RequestJoinPledge();
            case 39 -> new RequestAnswerJoinPledge();
            case 40 -> new RequestWithdrawalPledge();
            case 41 -> new RequestOustPledgeMember();
            case 44 -> new RequestGetItemFromPet();
            case 46 -> new RequestAllyInfo();
            case 47 -> new RequestCrystallizeItem();
            case 49 -> new SetPrivateStoreSellList();
            case 51 -> new RequestTeleport();
            case 55 -> new RequestSellItem();
            case 56 -> new RequestMagicSkillList();
            case 57 -> new RequestMagicSkillUse();
            case 58 -> new Appearing();
            case 59 -> {
                if (Config.ALLOW_WAREHOUSE) {
                    yield new SendWareHouseDepositList();
                }
                yield null;
            }
            case 60 -> new SendWareHouseWithDrawList();
            case 61 -> new RequestShortCutReg();
            case 63 -> new RequestShortCutDel();
            case 64 -> new RequestBuyItem();
            case 66 -> new RequestJoinParty();
            case 67 -> new RequestAnswerJoinParty();
            case 68 -> new RequestWithDrawalParty();
            case 69 -> new RequestOustPartyMember();
            case 70 -> new RequestDismissParty();
            case 71 -> new CannotMoveAnymore();
            case 72 -> new RequestTargetCanceld();
            case 73 -> new Say2C();
            case 74 -> this.handleMultiPacket(buffer, client);
            case 77 -> new RequestPledgeMemberList();
            case 80 -> new RequestSkillList();
            case 82 -> new MoveWithDelta();
            case 83 -> new RequestGetOnVehicle();
            case 84 -> new RequestGetOffVehicle();
            case 85 -> new AnswerTradeRequest();
            case 86 -> new RequestActionUse();
            case 87 -> new RequestRestart();
            case 88 -> new RequestSiegeInfo();
            case 89 -> new ValidatePosition();
            case 90 -> new RequestSEKCustom();
            case 91 -> new StartRotatingC();
            case 92 -> new FinishRotatingC();
            case 94 -> new RequestShowBoard();
            case 95 -> new GFRequestEnchantItem();
            case 96 -> new RequestDestroyItem();
            case 98 -> new RequestQuestList();
            case 99 -> new RequestQuestAbort();
            case 101 -> new RequestPledgeInfo();
            case 102 -> new RequestPledgeExtendedInfo();
            case 103 -> new RequestPledgeCrest();
            case 106 -> new RequestFriendInfoList();
            case 107 -> new RequestSendL2FriendSay();
            case 108 -> new RequestShowMiniMap();
            case 109 -> new RequestSendMsnChatLog();
            case 110 -> new RequestReload();
            case 111 -> new RequestHennaEquip();
            case 112 -> new RequestHennaUnequipList();
            case 113 -> new RequestHennaUnequipInfo();
            case 114 -> new RequestHennaUnequip();
            case 115 -> new RequestAquireSkillInfo();
            case 116 -> new SendBypassBuildCmd();
            case 117 -> new RequestMoveToLocationInVehicle();
            case 118 -> new CannotMoveAnymoreInVehicle();
            case 119 -> new RequestFriendInvite();
            case 120 -> new RequestFriendAddReply();
            case 121 -> new RequestFriendList();
            case 122 -> new RequestFriendDel();
            case 124 -> new RequestAquireSkill();
            case 125 -> new RequestRestartPoint();
            case 126 -> new RequestGMCommand();
            case 127 -> new RequestPartyMatchConfig();
            case 128 -> new RequestPartyMatchList();
            case 129 -> new RequestPartyMatchDetail();
            case 130 -> new RequestPrivateStoreList();
            case 131 -> new RequestPrivateStoreBuy();
            case 133 -> new RequestTutorialLinkHtml();
            case 134 -> new RequestTutorialPassCmdToServer();
            case 135 -> new RequestTutorialQuestionMark();
            case 136 -> new RequestTutorialClientEvent();
            case 137 -> new RequestPetition();
            case 138 -> new RequestPetitionCancel();
            case 139 -> new RequestGmList();
            case 140 -> new RequestJoinAlly();
            case 141 -> new RequestAnswerJoinAlly();
            case 142 -> new RequestWithdrawAlly();
            case 143 -> new RequestOustAlly();
            case 144 -> new RequestDismissAlly();
            case 145 -> new RequestSetAllyCrest();
            case 146 -> new RequestAllyCrest();
            case 147 -> new RequestChangePetName();
            case 148 -> new RequestPetUseItem();
            case 149 -> new RequestGiveItemToPet();
            case 150 -> new RequestPrivateStoreQuitSell();
            case 151 -> new SetPrivateStoreMsgSell();
            case 152 -> new RequestPetGetItem();
            case 153 -> new RequestPrivateStoreBuyManage();
            case 154 -> new SetPrivateStoreBuyList();
            case 156 -> new RequestPrivateStoreQuitBuy();
            case 157 -> new SetPrivateStoreMsgBuy();
            case 159 -> new RequestPrivateStoreBuySellList();
            case 160 -> new RequestTimeCheck();
            case 166 -> new RequestSkillCoolTime();
            case 167 -> new RequestPackageSendableItemList();
            case 168 -> new RequestPackageSend();
            case 169 -> new RequestBlock();
            case 171 -> new RequestCastleSiegeAttackerList();
            case 172 -> new RequestCastleSiegeDefenderList();
            case 173 -> new RequestJoinCastleSiege();
            case 174 -> new RequestConfirmCastleSiegeWaitingList();
            case 175 -> new RequestSetCastleSiegeTime();
            case 176 -> new RequestMultiSellChoose();
            case 177 -> new NetPing();
            case 178 -> new RequestRemainTime();
            case 179 -> new BypassUserCmd();
            case 180 -> new SnoopQuit();
            case 181 -> new RequestRecipeBookOpen();
            case 182 -> new RequestRecipeItemDelete();
            case 183 -> new RequestRecipeItemMakeInfo();
            case 184 -> new RequestRecipeItemMakeSelf();
            case 186 -> new RequestRecipeShopMessageSet();
            case 187 -> new RequestRecipeShopListSet();
            case 188 -> new RequestRecipeShopManageQuit();
            case 189 -> new RequestRecipeShopManageCancel();
            case 190 -> new RequestRecipeShopMakeInfo();
            case 191 -> new RequestRecipeShopMakeDo();
            case 192 -> new RequestRecipeShopSellList();
            case 193 -> new RequestObserverEnd();
            case 195 -> new RequestHennaList();
            case 196 -> new RequestHennaItemInfo();
            case 197 -> new RequestBuySeed();
            case 198 -> new ConfirmDlg();
            case 199 -> new RequestPreviewItem();
            case 200 -> new RequestSSQStatus();
            case 201 -> new PetitionVote();
            case 204 -> new RequestPledgePower();
            case 205 -> new RequestMakeMacro();
            case 206 -> new RequestDeleteMacro();
            case 208 -> this.handleExPacket(buffer, client, ClientState.IN_GAME);
            case 248 -> new RequestNewEnchantClose();
            default -> this.handleScriptPacket(client, opcode);
        };
    }

    private ReceivablePacket<GameClient> handleExPacket(ByteBuffer buffer, GameClient client, ClientState state) {
        ReceivablePacket<GameClient> packet;
        if (buffer.remaining() < 2) {
            if (GiranForgeConfig.PACKET_DEBUG_MODE) {
                Log.network((String)("Client: " + client + " sent a 0xD0 without the second opcode."));
            }
            return null;
        }
        int exOpcode = buffer.getShort() & 0xFFFF;
        if (GiranForgeConfig.PACKET_DEBUG_MODE) {
            System.out.print("\n[============= Extended Packet Received ================]\n");
            LOGGER.info("Extended Opcode: 0x{}:0x{} ({}:{})", new Object[]{GamePacketHandler.formatHexByte(208), GamePacketHandler.formatHexShort(exOpcode), 208, exOpcode});
            LOGGER.info("State: {}", (Object)state);
            System.out.print("[=======================================================]\n");
        }
        if (CGModule.getInstance().isActive() && (packet = CGModule.getInstance().handlePacket(client, 208, Integer.valueOf(exOpcode))) != null) {
            if (GiranForgeConfig.PACKET_DEBUG_MODE) {
                this.logExtendedPacketCreated(packet, state, 208, exOpcode);
            }
            return packet;
        }
        if (!client.getPacketFilter().checkPacketEx(208, exOpcode)) {
            if (GiranForgeConfig.PACKET_DEBUG_MODE) {
                LOGGER.warn("Extended packet filtered out: 0x{}:0x{}", (Object)GamePacketHandler.formatHexByte(208), (Object)GamePacketHandler.formatHexShort(exOpcode));
            }
            return null;
        }
        packet = null;
        switch (state) {
            default: {
                throw new IncompatibleClassChangeError();
            }
            case CONNECTED: {
                packet = this.handleExConnected(exOpcode, client);
                break;
            }
            case AUTHED: {
                packet = this.handleExAuthed(exOpcode, client);
                break;
            }
            case IN_GAME: {
                packet = this.handleExInGame(exOpcode, buffer, client);
            }
        }
        if (packet != null && GiranForgeConfig.PACKET_DEBUG_MODE) {
            this.logExtendedPacketCreated(packet, state, 208, exOpcode);
        } else if (packet == null && GiranForgeConfig.PACKET_DEBUG_MODE) {
            this.logUnknownExtendedPacket(client, 208, exOpcode, state);
        }
        return packet;
    }

    private ReceivablePacket<GameClient> handleExConnected(int exOpcode, GameClient client) {
        return this.handleScriptExPacket(client, 208, exOpcode);
    }

    private ReceivablePacket<GameClient> handleExAuthed(int exOpcode, GameClient client) {
        return switch (exOpcode) {
            case 51 -> new GotoLobby();
            case 166 -> new RequestEx2ndPasswordCheck();
            case 167 -> new RequestEx2ndPasswordVerify();
            case 168 -> new RequestEx2ndPasswordReq();
            case 169 -> new RequestCharacterNameCreatable();
            case 182, 255, 351 -> DummyClientPacket.STATIC_DUMMY_CLIENT_PACKET;
            case 260 -> new ExSendClientINI();
            default -> this.handleScriptExPacket(client, 208, exOpcode);
        };
    }

    private ReceivablePacket<GameClient> handleExInGame(int exOpcode, ByteBuffer buffer, GameClient client) {
        return switch (exOpcode) {
            case 1 -> new RequestManorList();
            case 2 -> new RequestProcureCropList();
            case 3 -> new RequestSetSeed();
            case 4 -> new RequestSetCrop();
            case 5 -> new RequestWriteHeroWords();
            case 6 -> new RequestExMPCCAskJoin();
            case 7 -> new RequestExMPCCAcceptJoin();
            case 8 -> new RequestExOustFromMPCC();
            case 9 -> new RequestOustFromPartyRoom();
            case 10 -> new RequestDismissPartyRoom();
            case 11 -> new RequestWithdrawPartyRoom();
            case 12 -> new RequestHandOverPartyMaster();
            case 13 -> new RequestAutoSoulShot();
            case 14 -> new RequestExEnchantSkillInfo();
            case 15 -> new RequestExEnchantSkill();
            case 16 -> new RequestPledgeEmblem();
            case 17 -> new RequestSetPledgeCrestLarge();
            case 18 -> new RequestPledgeSetAcademyMaster();
            case 19 -> new RequestPledgePowerGradeList();
            case 20 -> new RequestPledgeMemberPowerInfo();
            case 21 -> new RequestPledgeSetMemberPowerGrade();
            case 22 -> new RequestPledgeMemberInfo();
            case 23 -> new RequestPledgeWarList();
            case 24 -> new RequestExFishRanking();
            case 25 -> new RequestPCCafeCouponUse();
            case 27 -> new RequestDuelStart();
            case 28 -> new RequestDuelAnswerStart();
            case 29 -> new RequestTutorialClientEvent();
            case 30 -> new RequestExRqItemLink();
            case 32 -> new RequestExMoveToLocationInAirShip();
            case 33 -> new RequestKeyMapping();
            case 34 -> new RequestSaveKeyMapping();
            case 35 -> new RequestExRemoveItemAttribute();
            case 36 -> new RequestSaveInventoryOrder();
            case 37 -> new RequestExitPartyMatchingWaitingRoom();
            case 38 -> new RequestConfirmTargetItem();
            case 39 -> new RequestConfirmRefinerItem();
            case 40 -> new RequestConfirmGemStone();
            case 41 -> new RequestOlympiadObserverEnd();
            case 42 -> new RequestCursedWeaponList();
            case 43 -> new RequestCursedWeaponLocation();
            case 44 -> new RequestPledgeReorganizeMember();
            case 45 -> new RequestExMPCCShowPartyMembersInfo();
            case 46 -> new RequestExOlympiadObserverEnd();
            case 47 -> new RequestAskJoinPartyRoom();
            case 48 -> new AnswerJoinPartyRoom();
            case 49 -> new RequestListPartyMatchingWaitingRoom();
            case 50 -> new RequestEnchantItemAttribute();
            case 53 -> new RequestExMoveToLocationAirShip();
            case 54 -> new RequestBidItemAuction();
            case 55 -> new RequestInfoItemAuction();
            case 56 -> new RequestExChangeName();
            case 57 -> new RequestAllCastleInfo();
            case 58 -> new RequestAllFortressInfo();
            case 59 -> new RequestAllAgitInfo();
            case 60 -> new RequestFortressSiegeInfo();
            case 61 -> new RequestGetBossRecord();
            case 62 -> new RequestRefine();
            case 63 -> new RequestConfirmCancelItem();
            case 64 -> new RequestRefineCancel();
            case 65 -> new RequestExMagicSkillUseGround();
            case 66 -> new RequestDuelSurrender();
            case 67 -> new RequestExEnchantSkillInfoDetail();
            case 69 -> new RequestFortressMapInfo();
            case 70 -> new RequestPVPMatchRecord();
            case 71 -> new SetPrivateStoreWholeMsg();
            case 72 -> new RequestDispel();
            case 73 -> new RequestExTryToPutEnchantTargetItem();
            case 74 -> new RequestExTryToPutEnchantSupportItem();
            case 75 -> new RequestExCancelEnchantItem();
            case 76 -> new RequestChangeNicknameColor();
            case 77 -> new RequestResetNickname();
            case 78 -> this.handleBookMarkPacket(buffer, client);
            case 79 -> new RequestWithDrawPremiumItem();
            case 80 -> new RequestExJump();
            case 81 -> new RequestExStartShowCrataeCubeRank();
            case 82 -> new RequestExStopShowCrataeCubeRank();
            case 83 -> new NotifyStartMiniGame();
            case 84 -> new RequestExJoinDominionWar();
            case 85 -> new RequestExDominionInfo();
            case 86 -> new RequestExCleftEnter();
            case 87 -> new RequestExCubeGameChangeTeam();
            case 88 -> new RequestExEndScenePlayer();
            case 89 -> new RequestExCubeGameReadyAnswer();
            case 90 -> new RequestExListMpccWaiting();
            case 91 -> new RequestExManageMpccRoom();
            case 92 -> new RequestExJoinMpccRoom();
            case 93 -> new RequestExOustFromMpccRoom();
            case 94 -> new RequestExDismissMpccRoom();
            case 95 -> new RequestExWithdrawMpccRoom();
            case 96 -> new RequestExSeedPhase();
            case 97 -> new RequestExMpccPartymasterList();
            case 98 -> new RequestExPostItemList();
            case 99 -> new RequestExSendPost();
            case 100 -> new RequestExRequestReceivedPostList();
            case 101 -> new RequestExDeleteReceivedPost();
            case 102 -> new RequestExRequestReceivedPost();
            case 103 -> new RequestExReceivePost();
            case 104 -> new RequestExRejectPost();
            case 105 -> new RequestExRequestSentPostList();
            case 106 -> new RequestExDeleteSentPost();
            case 107 -> new RequestExRequestSentPost();
            case 108 -> new RequestExCancelSentPost();
            case 109 -> new RequestExShowNewUserPetition();
            case 110 -> new RequestExShowStepTwo();
            case 111 -> new RequestExShowStepThree();
            case 114 -> new RequestExRefundItem();
            case 115 -> new RequestExBuySellUIClose();
            case 116 -> new RequestExEventMatchObserverEnd();
            case 117 -> new RequestPartyLootModification();
            case 118 -> new AnswerPartyLootModification();
            case 119 -> new AnswerCoupleAction();
            case 120 -> new RequestExBR_EventRankerList();
            case 122 -> new RequestAddExpandQuestAlarm();
            case 123 -> new RequestVoteNew();
            case 129 -> new RequestExAddPostFriendForPostBox();
            case 130 -> new RequestExDeletePostFriendForPostBox();
            case 131 -> new RequestExShowPostFriendListForPostBox();
            case 132 -> new RequestExFriendListForPostBox();
            case 133 -> new RequestOlympiadMatchList();
            case 134 -> new RequestExBR_GamePoint();
            case 135 -> new RequestExBR_ProductList();
            case 136 -> new RequestExBR_ProductInfo();
            case 137 -> new RequestExBR_BuyProduct();
            case 138 -> new RequestExBR_RecentProductList();
            case 139 -> new RequestExBR_MiniGameLoadScores();
            case 140 -> new RequestExBR_MiniGameInsertScore();
            case 141 -> new RequestExBR_LectureMark();
            case 142 -> new RequestCrystallizeEstimate();
            case 143 -> new RequestCrystallizeItemCancel();
            case 148 -> new RequestFriendDetailInfo();
            case 149 -> new RequestUpdateFriendMemo();
            case 150 -> new RequestUpdateBlockMemo();
            case 152 -> this.createCommissionPacket("RequestCommissionRegistrableItemList");
            case 153 -> this.createCommissionPacket("RequestCommissionInfo");
            case 154 -> this.createCommissionPacket("RequestCommissionRegister");
            case 155 -> this.createCommissionPacket("RequestCommissionCancel");
            case 156 -> this.createCommissionPacket("RequestCommissionDelete");
            case 157 -> this.createCommissionPacket("RequestCommissionList");
            case 158 -> this.createCommissionPacket("RequestCommissionBuyInfo");
            case 159 -> this.createCommissionPacket("RequestCommissionBuyItem");
            case 160 -> this.createCommissionPacket("RequestCommissionRegisteredItem");
            case 170 -> new RequestGoodsInventoryInfo();
            case 174 -> new RequestHardWareInfo();
            case 182, 267, 290, 291, 352 -> DummyClientPacket.STATIC_DUMMY_CLIENT_PACKET;
            case 185 -> new RequestClanAskJoinByName();
            case 186 -> new RequestInzoneWaitingTime();
            case 196 -> new RequestAppearanceTarget();
            case 197 -> new RequestAppearanceExtraction();
            case 198 -> new RequestAppearanceCancel();
            case 199 -> new RequestAppearanceModify();
            case 211 -> new RequestPledgeRecruitInfo();
            case 212 -> new RequestPledgeRecruitBoardSearch();
            case 213 -> new RequestPledgeRecruitBoardAccess();
            case 214 -> new RequestPledgeRecruitBoardDetail();
            case 215 -> new RequestPledgeWaitingApply();
            case 216 -> new RequestPledgeWaitingApplied();
            case 217 -> new RequestPledgeWaitingList();
            case 218 -> new RequestPledgeWaitingUser();
            case 219 -> new RequestPledgeWaitingUserAccept();
            case 220 -> new RequestPledgeDraftListSearch();
            case 221 -> new RequestPledgeDraftListApply();
            case 222 -> new RequestPledgeRecruitApplyInfo();
            case 227 -> new RequestExAddEnchantScrollItem();
            case 228 -> new RequestExRemoveEnchantSupportItem();
            case 230 -> new RequestDivideAdenaStart();
            case 231 -> new RequestDivideAdenaCancel();
            case 232 -> new RequestDivideAdena();
            case 240 -> new ExPCCafeRequestOpenWindowWithoutNPC();
            case 244 -> new RequestNewEnchantPushOne();
            case 245 -> new RequestNewEnchantRemoveOne();
            case 246 -> new RequestNewEnchantPushTwo();
            case 247 -> new RequestNewEnchantRemoveTwo();
            case 248 -> new RequestNewEnchantClose();
            case 249 -> new RequestNewEnchantTry();
            case 250 -> new RequestNewEnchantRetryToPutItems();
            case 254 -> new RequestTargetActionMenu();
            case 255, 351 -> new RequestUserBanInfo();
            case 261 -> new ExAutoFish();
            case 264 -> new RequestExItemEnsoul();
            case 266 -> new RequestVipProductList();
            case 270 -> new RequestVipInfo();
            case 273 -> new RequestPledgeSignInForOpenJoiningMethod();
            case 286 -> new RequestTodoList();
            case 288 -> new RequestOneDayRewardReceive();
            case 295 -> new RequestBlockMemoInfo();
            case 296 -> new RequestTryEnSoulExtraction();
            case 297 -> new RequestRaidBossSpawnInfo();
            case 298 -> new RequestRaidServerInfo();
            case 310 -> this.createPacketByName("RequestUpgradeSystemResult");
            case 354 -> new RequestNormalUpgradeSystem();
            case 359 -> new RequestExCostumeUseItem();
            case 360 -> new RequestExCostumeList();
            case 361 -> new RequestExCostumeCollectionSkillActive();
            case 362 -> new RequestExCostumeEvolution();
            case 363 -> new RequestExCostumeExtract();
            case 364 -> new RequestExCostumeLock();
            case 365 -> new RequestExCostumeChangeShortcut();
            default -> this.handleScriptExPacket(client, 208, exOpcode);
        };
    }

    private ReceivablePacket<GameClient> handleBookMarkPacket(ByteBuffer buffer, GameClient client) {
        int subOpcode = buffer.getInt();
        return switch (subOpcode) {
            case 0 -> null;
            case 1 -> new RequestSaveBookMarkSlot();
            case 2 -> new RequestModifyBookMarkSlot();
            case 3 -> new RequestDeleteBookMarkSlot();
            case 4 -> new RequestTeleportBookMark();
            default -> this.handleScriptExPacket(client, 208, subOpcode);
        };
    }

    private ReceivablePacket<GameClient> handleMultiPacket(ByteBuffer buffer, GameClient client) {
        int subOpcode = buffer.get() & 0xFF;
        switch (subOpcode) {
            default: 
        }
        return this.handleScriptExPacket(client, 74, subOpcode);
    }

    private ReceivablePacket<GameClient> handleScriptPacket(GameClient client, int opcode) {
        List<Scripts.ScriptClassAndMethod> handlers = (List<Scripts.ScriptClassAndMethod>)Scripts.onScriptPacket.get(opcode);
        if (handlers != null && !handlers.isEmpty()) {
            return new ScriptPacket().setOp(Integer.valueOf(opcode)).setHandlers(handlers);
        }
        System.out.print("\n");
        LOGGER.warn("Unknown packet {}, state: {}", (Object)GamePacketHandler.formatOpcodeHex(opcode), (Object)client.getState());
        client.onUnknownPacket();
        return null;
    }

    private ReceivablePacket<GameClient> handleScriptExPacket(GameClient client, int opcode, int exOpcode) {
        List<Scripts.ScriptClassAndMethod> handlers = (List<Scripts.ScriptClassAndMethod>)Scripts.onScriptExPacket.get(exOpcode);
        if (handlers != null && !handlers.isEmpty()) {
            return new ScriptExPacket().setOpEx(Integer.valueOf(exOpcode)).setOp(Integer.valueOf(opcode)).setHandlers(handlers);
        }
        System.out.print("\n");
        LOGGER.info("Unknown packet {}, {} {} state: {}", new Object[]{GamePacketHandler.formatOpcodeHex(opcode, exOpcode), opcode, exOpcode, client.getState()});
        client.onUnknownPacket();
        return null;
    }

    private void logPacketCreated(ReceivablePacket<GameClient> packet, GameClient.GameClientState state, int opcode) {
        if (packet == null || !GiranForgeConfig.PACKET_DEBUG_MODE) {
            return;
        }
        System.out.print("\n[============= Packet Created Successfully =============]\n");
        LOGGER.info("Class: {}", (Object)packet.getClass().getSimpleName());
        LOGGER.info("Opcode: 0x{} ({})", (Object)GamePacketHandler.formatHexByte(opcode), (Object)opcode);
        LOGGER.info("State: {}", (Object)state);
        LOGGER.info("Status: VALID");
        System.out.print("[=======================================================]\n");
    }

    private void logExtendedPacketCreated(ReceivablePacket<GameClient> packet, ClientState state, int baseOpcode, int exOpcode) {
        if (packet == null || !GiranForgeConfig.PACKET_DEBUG_MODE) {
            return;
        }
        System.out.print("\n[============= Extended Packet Created =================]\n");
        LOGGER.info("Class: {}", (Object)packet.getClass().getSimpleName());
        LOGGER.info("Extended Opcode: 0x{}:0x{} ({}:{})", new Object[]{GamePacketHandler.formatHexByte(baseOpcode), GamePacketHandler.formatHexShort(exOpcode), baseOpcode, exOpcode});
        LOGGER.info("State: {}", (Object)state);
        LOGGER.info("Status: VALID");
        System.out.print("[=======================================================]\n\n");
    }

    private void logUnknownPacket(GameClient client, ByteBuffer buffer, int initialPosition, int opcode) {
        if (!GiranForgeConfig.PACKET_DEBUG_MODE) {
            return;
        }
        if (opcode == 208) {
            return;
        }
        System.out.print("\n[================= Unknown Packet =====================]\n");
        LOGGER.warn("Opcode: 0x{} ({})", (Object)GamePacketHandler.formatHexByte(opcode), (Object)opcode);
        LOGGER.warn("State: {}", (Object)client.getState());
        LOGGER.info("Add a case in the handle{}State()", (Object)client.getState().toString());
        if (GiranForgeConfig.PACKET_DEBUG_MODE) {
            buffer.position(initialPosition);
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            System.out.println("Hex dump do pacote desconhecido:\n" + GamePacketHandler.formatHexDump(data));
        }
        System.out.print("\n[=======================================================]\n");
    }

    private void logUnknownExtendedPacket(GameClient client, int baseOpcode, int exOpcode, ClientState state) {
        if (!GiranForgeConfig.PACKET_DEBUG_MODE) {
            return;
        }
        System.out.print("\n[============= Unknown Extended Packet ================]\n");
        LOGGER.warn("Extended Opcode: 0x{}:0x{} ({}:{})", new Object[]{GamePacketHandler.formatHexByte(baseOpcode), GamePacketHandler.formatHexShort(exOpcode), baseOpcode, exOpcode});
        LOGGER.warn("State: {}", (Object)state);
        LOGGER.info("Add a case in the handleExInGame()");
        System.out.print("[=======================================================]\n\n");
    }

    private ReceivablePacket<GameClient> createPacketByName(String className) {
        try {
            Class<?> packetClass = Class.forName("l2.gameserver.network.l2.c2s." + className);
            return (ReceivablePacket<GameClient>)packetClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception e) {
            return null;
        }
    }

    private ReceivablePacket<GameClient> createCommissionPacket(String className) {
        try {
            Class<?> packetClass = Class.forName("l2.gameserver.network.l2.c2s.commission." + className);
            return (ReceivablePacket<GameClient>)packetClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception e) {
            return null;
        }
    }

    public GameClient create(MMOConnection<GameClient> connection) {
        return new GameClient(connection);
    }

    public void execute(Runnable runnable) {
        ThreadPoolManager.getInstance().execute(runnable);
    }

    public void addWhitelistedIp(String ip) {
        this.whitelistedIps.add(ip);
    }

    public boolean isWhitelistedIp(String ip) {
        return this.whitelistedIps.contains(ip) || Config.VALID_IPS_LIST.contains(ip);
    }

    public boolean accept(SocketChannel socketChannel) {
        try {
            String ip = socketChannel.socket().getInetAddress().getHostAddress();
            return !Config.REJECT_INVALID_CONNECTIONS || this.whitelistedIps.contains(ip) || Config.VALID_IPS_LIST.contains(ip);
        }
        catch (Exception e) {
            LOGGER.error("Accept interrupted", (Throwable)e);
            return true;
        }
    }

    private static enum ClientState {
        CONNECTED,
        AUTHED,
        IN_GAME;

    }
}

