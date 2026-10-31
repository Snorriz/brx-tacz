package net.mcreator.miklosmilitaryarmorr.block;

import net.mcreator.miklosmilitaryarmorr.procedures.Sandbags4OnBlockRightClickedProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Sandbags4Block extends Block {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.f_54117_;

   public Sandbags4Block() {
      super(Properties.m_284310_().m_60918_(SoundType.f_56746_).m_60913_(1.1F, 9.0F).m_60956_(0.9F).m_60967_(0.9F).m_60955_().m_60924_((bs, br, bp) -> false));
      this.m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_(FACING, Direction.NORTH));
   }

   public boolean m_7420_(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }

   public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }

   public VoxelShape m_5909_(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.m_83040_();
   }

   public VoxelShape m_5940_(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return switch ((Direction)state.m_61143_(FACING)) {
         case NORTH -> Shapes.m_83124_(
            m_49796_(16.0, 0.0, 3.5, 30.0, 6.0, 12.5),
            new VoxelShape[]{m_49796_(-14.0, 0.0, 3.5, 0.0, 6.0, 12.5), m_49796_(1.0, 0.0, 3.5, 15.0, 6.0, 12.5), m_49796_(8.5, 6.0, 3.5, 22.5, 12.0, 12.5)}
         );
         case EAST -> Shapes.m_83124_(
            m_49796_(3.5, 0.0, 16.0, 12.5, 6.0, 30.0),
            new VoxelShape[]{m_49796_(3.5, 0.0, -14.0, 12.5, 6.0, 0.0), m_49796_(3.5, 0.0, 1.0, 12.5, 6.0, 15.0), m_49796_(3.5, 6.0, 8.5, 12.5, 12.0, 22.5)}
         );
         case WEST -> Shapes.m_83124_(
            m_49796_(3.5, 0.0, -14.0, 12.5, 6.0, 0.0),
            new VoxelShape[]{m_49796_(3.5, 0.0, 16.0, 12.5, 6.0, 30.0), m_49796_(3.5, 0.0, 1.0, 12.5, 6.0, 15.0), m_49796_(3.5, 6.0, -6.5, 12.5, 12.0, 7.5)}
         );
         default -> Shapes.m_83124_(
            m_49796_(-14.0, 0.0, 3.5, 0.0, 6.0, 12.5),
            new VoxelShape[]{m_49796_(16.0, 0.0, 3.5, 30.0, 6.0, 12.5), m_49796_(1.0, 0.0, 3.5, 15.0, 6.0, 12.5), m_49796_(-6.5, 6.0, 3.5, 7.5, 12.0, 12.5)}
         );
      };
   }

   protected void m_7926_(Builder<Block, BlockState> builder) {
      super.m_7926_(builder);
      builder.m_61104_(new Property[]{FACING});
   }

   public BlockState m_5573_(BlockPlaceContext context) {
      return (BlockState)super.m_5573_(context).m_61124_(FACING, context.m_8125_().m_122424_());
   }

   public BlockState m_6843_(BlockState state, Rotation rot) {
      return (BlockState)state.m_61124_(FACING, rot.m_55954_((Direction)state.m_61143_(FACING)));
   }

   public BlockState m_6943_(BlockState state, Mirror mirrorIn) {
      return state.m_60717_(mirrorIn.m_54846_((Direction)state.m_61143_(FACING)));
   }

   public InteractionResult m_6227_(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
      super.m_6227_(blockstate, world, pos, entity, hand, hit);
      int x = pos.m_123341_();
      int y = pos.m_123342_();
      int z = pos.m_123343_();
      double hitX = hit.m_82450_().f_82479_;
      double hitY = hit.m_82450_().f_82480_;
      double hitZ = hit.m_82450_().f_82481_;
      Direction direction = hit.m_82434_();
      Sandbags4OnBlockRightClickedProcedure.execute(world, x, y, z, entity);
      return InteractionResult.SUCCESS;
   }
}
